package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMstRadar;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowFof2;
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
 * @date 2022/11/24 14:06
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherRegionNowFof2", getAction = SpaceWeatherRegionNowFof2.class)
public class SpaceWeatherRegionNowFof2Action implements MessageAction<SpaceWeatherRegionNowFof2> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherRegionNowFof2> message) {
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
                String r12="";
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
                           r12=strs[6];
                           all=date+" "+time+" "+lats+" "+late+" "+latstep+" "+lons+" "+lone+" "+lonstep+" "+r12;
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
