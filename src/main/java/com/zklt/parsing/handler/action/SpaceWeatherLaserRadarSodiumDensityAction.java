package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherLaserRadarAtmTem;
import com.zklt.parsing.model.entity.SpaceWeatherLaserRadarSodiumDensity;
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
 * @date 2022/11/23 10:44
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherLaserRadarSodiumDensity", getAction = SpaceWeatherLaserRadarSodiumDensity.class)
public class SpaceWeatherLaserRadarSodiumDensityAction implements MessageAction<SpaceWeatherLaserRadarSodiumDensity> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherLaserRadarSodiumDensity> message) {
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
                String stationCode="";
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)){
                        String[] timepd=line.split(" ");
                        if (i==0){
                            stationCode=line;
                        }else if (timepd.length==1){
                            String year=line.substring(0,4);
                            String month=line.substring(4,6);
                            String day=line.substring(6,8);
                            String hour=line.substring(8,10);
                            String minute=line.substring(10,12);
                            String second=line.substring(12,14);
                            date=year+"-"+month+"-"+day;
                            time=hour+":"+minute+":"+second;
                        } else {
                            String returndata=date+" "+time+" "+stationCode+" "+line;
                            result.add(returndata);
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
