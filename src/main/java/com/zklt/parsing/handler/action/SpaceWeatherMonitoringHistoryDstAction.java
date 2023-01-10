package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDst;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringHistoryDst;
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
 * @date 2022/11/28 20:19
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringHistoryDst", getAction = SpaceWeatherMonitoringHistoryDst.class)
public class SpaceWeatherMonitoringHistoryDstAction implements MessageAction<SpaceWeatherMonitoringHistoryDst> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringHistoryDst> message) {
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
                      /*  String linenew=  line.replace("-"," -");
                        linenew=  linenew.replace("PP"," ");
                        linenew = linenew.trim().replaceAll(" +", " ");
                        String[] dates = linenew.split(" ");*/
                        String date=line.substring(14,16)+line.substring(3,5)+"-"+line.substring(5,7)+"-"+line.substring(8,10);
                        String type=line.substring(13,14);
                        String basevalue=line.substring(16,20);
                        String meanvalue=line.substring(116,120);

                        int h=0;
                        int start=20;
                        int end=24;
                        for (int j=1;j<=24;j++) {
                            String dst=line.substring(start,end);
                            String hour="";
                            if (h<10) {
                                hour="0"+h+":00:00";
                            }else {
                                hour=h+":00:00";
                            }
                            String returnstr=date+" "+hour+" "+dst+" "+type+" "+basevalue+" "+meanvalue;
                            result.add(returnstr);
                            h++;
                            start=end;
                            end=end+4;
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
