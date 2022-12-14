package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowNcasts4;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowTec;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/24 15:51
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherRegionNowTec", getAction = SpaceWeatherRegionNowTec.class)
public class SpaceWeatherRegionNowTecAction implements MessageAction<SpaceWeatherRegionNowTec> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherRegionNowTec> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try (InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(input)){
                String line = null;
                //避免数据中间出现空格所以获取数据总行数进行遍历
                long countLine = Files.lines(file.toPath()).count();
                String date="";
                String time="";

                String lats="";
                String late="";
                String latstep="";
                String lons="";
                String lone="";
                String lonstep="";
                String all="";
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)) {
                        line=line.replaceAll("\t"," ");
                        if (i==0){
                            line = line.trim().replaceAll(" +", " ");
                            String[] datetime=line.split(" ");
                            date=datetime[0]+"-"+datetime[1]+"-"+datetime[2];
                            time=datetime[3]+":"+"00"+":"+"00";
                        }else if (i==1){
                            line = line.trim().replaceAll(" +", " ");
                            String[] strs=line.split(" ");
                            lats=strs[0];
                            late=strs[1];
                            latstep=strs[2];
                            lons=strs[3];
                            lone=strs[4];
                            lonstep=strs[5];
                            all=date+" "+time+" "+lats+" "+late+" "+latstep+" "+lons+" "+lone+" "+lonstep;
                        }else {
                            String allre=all+" "+line;
                            result.add(allre);
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
