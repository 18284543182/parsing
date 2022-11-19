package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringApaAndF105Value;
import com.zklt.parsing.model.enums.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/19 18:48
 * @Description:
 */
@Service
@Slf4j
@Mapper(type = "SpaceWeatherMonitoringApaAndF105Value", getAction = SpaceWeatherMonitoringApaAndF105Value.class)
public class SpaceWeatherMonitoringApaAndF107ValueAction implements MessageAction<SpaceWeatherMonitoringApaAndF105Value> {

    private String apType = "AP";
    private String f107Type = "F10.7";

    private String jumpValue = "FORECASTER:";

    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringApaAndF105Value> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try (InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(input)) {
            String line = null;
            while((line = br.readLine())!=null){
                if (checkLine(line)){
                    if (line.contains(apType)){
                        line = get(br,result, line,apType,f107Type);
                    }
                    if (line!=null&&line.contains(f107Type)){
                        get(br,result, line,f107Type,jumpValue);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public String getLine(StringBuffer line,BufferedReader br ) throws IOException {
            line.append(br.readLine());

        return line.toString();
    }

    public boolean checkLine(String line) {
         return !StringUtils.isEmpty(line) &&
                 !line.startsWith("#") &&
                 !line.startsWith(":")&&
                 !line.contains(jumpValue)&&
                 !line.contains("99999")&&
                 !line.contains("NNNN");
    }

    public String get(BufferedReader br ,List<String> list, String line,String type,String elseType) throws IOException {
        //首次进来一定是类型所以先判断类型
        if (line.contains(type)){
            if (type.equals(apType)){
                type = "Ap";
            }
            while ((line = br.readLine())!=null){
                if (line.contains(elseType)){
                    return line;
                }
                String[] lineArr = line.trim().split(" ");
                for (int i = 0;i<lineArr.length;i=i+2){
                    String temp = lineArr[i] + " " + lineArr[i+1] + " " + type;
                    list.add(temp);
                }
            }
        }
        return null;
    }
}
