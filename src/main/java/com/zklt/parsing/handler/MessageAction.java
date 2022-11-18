package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 17:09
 * @Description:
 */
public interface MessageAction<T extends Message> {


    Object doAction(HandlerMessage<T> message);

    default List<String> readFile(File file){
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
                        result.add(line);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    default HandlerMessage<T> decode(String data, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        //把多个空格转换为一个空格
        data = data.trim().replaceAll(" +", " ");
        String[] dates = data.split(" ");
        Field[] files = clazz.getFields();
        if (dates.length!=files.length){
            return null;
        }
        HandlerMessage<T> resMessage = new HandlerMessage<>();
        T body = clazz.newInstance();
        for (int i= 0;i<files.length;i++){
            Field field = files[i];
            field.setAccessible(true);
            field.set(body,dates[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }

}
