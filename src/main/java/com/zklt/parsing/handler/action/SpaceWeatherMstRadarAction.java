package com.zklt.parsing.handler.action;


import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringWeeliypredictDo;
import com.zklt.parsing.model.entity.SpaceWeatherMstRadar;
import com.zklt.parsing.model.enums.Mapper;
import com.zklt.parsing.model.enums.MonthEnums;
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
 * @date 2022/11/21 10:09
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMstRadar", getAction = SpaceWeatherMstRadar.class)
public class SpaceWeatherMstRadarAction implements MessageAction<SpaceWeatherMstRadar> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMstRadar> message) {
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
                String instrumentId="";
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)){
                        if (i==0){
                            String yearm = line.trim().replaceAll(" +", " ");
                            String[] dates = yearm.split(" ");
                            int mo= Integer.parseInt(dates[1].trim());
                            if (mo<10){
                                dates[1]="0"+mo;
                            }
                            int day= Integer.parseInt(dates[2].trim());
                            if (day<10){
                                dates[2]="0"+day;
                            }
                            date=dates[0]+"-"+dates[1]+"-"+dates[2];

                            int hour= Integer.parseInt(dates[3].trim());
                            if (hour<10){
                                dates[3]="0"+hour;
                            }
                            int minute= Integer.parseInt(dates[4].trim());
                            if (minute<10){
                                dates[4]="0"+minute;
                            }
                            time=dates[3]+":"+dates[4]+":"+"00";
                            stationCode=dates[5];
                            instrumentId=dates[6];
                        }else {
                            String returndata=date+" "+time+" "+stationCode+" "+instrumentId+" "+line;
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
