package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringHistoryDst;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringOneMinuteAe;
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
 * @date 2022/11/28 21:08
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringOneMinuteAe", getAction = SpaceWeatherMonitoringOneMinuteAe.class)
public class SpaceWeatherMonitoringOneMinuteAeAction implements MessageAction<SpaceWeatherMonitoringOneMinuteAe> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringOneMinuteAe> message) {
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
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)){

                        line = line.trim().replaceAll(" +", " ");
                        String[] dates = line.split(" ");

                        String filename=file.getName();
                        String[] filenames=filename.split("_");
                        String year=filenames[1];

                        String identifier=dates[0];
                        String editionNumber=dates[2];
                        String meanvalue=dates[dates.length-1];

                        String date=year+"-"+dates[1].substring(2,4)+"-"+dates[1].substring(4,6);
                        String hour=dates[1].substring(7,9);

                        int h=0;
                        for (int j=3;j<dates.length-1;j++) {
                            String value=dates[j];
                            String time="";
                            if (h<10) {
                                time=hour+":"+"0"+h+":00";
                            }else {
                                time=hour+":"+h+":00";
                            }
                            String returnstr=date+" "+time+" "+identifier+" "+editionNumber+" "+value+" "+meanvalue;
                            result.add(returnstr);
                            h++;
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
