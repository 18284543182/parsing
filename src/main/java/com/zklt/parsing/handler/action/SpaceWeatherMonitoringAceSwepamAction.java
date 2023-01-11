package com.zklt.parsing.handler.action;

import com.alibaba.fastjson.JSON;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceSwepam;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

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
 * @date 2022/11/18 11:56
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringAceSwepam", getAction = SpaceWeatherMonitoringAceSwepam.class)
public class SpaceWeatherMonitoringAceSwepamAction implements MessageAction<SpaceWeatherMonitoringAceSwepam> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringAceSwepam> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
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
                            " "+strings.get(2)+" "+strings.get(3);
                    result.add(returnstr);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
