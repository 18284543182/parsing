package com.zklt.parsing.handler.action;

import com.alibaba.fastjson.JSON;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceMag;
import com.zklt.parsing.model.enums.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

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
 * @date 2022/11/17 18:26
 * @Description:
 */
@Service
@Slf4j
@Mapper(type = "SpaceWeatherMonitoringAceMag", getAction = SpaceWeatherMonitoringAceMag.class)
public class SpaceWeatherMonitoringAceMagAction implements MessageAction<SpaceWeatherMonitoringAceMag> {

    @Override
    public SpaceWeatherMonitoringAceMag doAction(HandlerMessage<SpaceWeatherMonitoringAceMag> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        StopWatch stopWatch = new StopWatch("磁场三分量、总磁场");
        stopWatch.start();
        log.info("StopWatch start '" + stopWatch.getId() + "': running time (millis) = " + stopWatch.getTotalTimeMillis());
        List<String> result = new ArrayList<>();
        try {
            List<Object> objects=new ArrayList<>();
            InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(input);
            String line = br.readLine();
            objects= JSON.parseArray(line, Object.class);
            for (int i=0;i<objects.size();i++){
                if (i>0){
                    Object object=objects.get(i);
                    List<String> strings= (List<String>) object;
                    String returnstr= strings.get(0)+" "+strings.get(1)+
                            " "+strings.get(2)+" "+strings.get(3)+
                            " "+strings.get(4)+" "+strings.get(5)+
                            " "+strings.get(6);
                    result.add(returnstr);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("StopWatch stop '" + stopWatch.getId() + "': running time (millis) = " + stopWatch.getTotalTimeMillis());
        stopWatch.stop();
        return result;
    }
}
