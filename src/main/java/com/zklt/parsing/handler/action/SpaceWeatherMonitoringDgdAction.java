package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDgd;
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
 * @date 2022/11/18 11:58
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringDgd", getAction = SpaceWeatherMonitoringDgd.class)
public class SpaceWeatherMonitoringDgdAction implements MessageAction<SpaceWeatherMonitoringDgd> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringDgd> message) {
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
                    if (!StringUtils.isEmpty(line) &&!line.startsWith("#")&&!line.startsWith(":")) {
                        String linenew=  line.replace("-"," -");
                        result.add(linenew);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
