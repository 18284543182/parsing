package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 17:09
 * @Description:
 */

public interface MessageAction<T extends Message> {


    String doAction(HandlerMessage<T> message);

    default List<String> readFile(File file){
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try (InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8"); BufferedReader br = new BufferedReader(input);){
                String line = null;
                while((line = br.readLine()) != null)
                {
                    result.add(line);
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
        if (dates.length!=clazz.getFields().length){
            return null;
        }
        HandlerMessage<T> resMessage = new HandlerMessage<>();
        T body = clazz.newInstance();
        for (int i= 0;i<dates.length;i++){
            clazz.getFields()[i].setAccessible(true);
            clazz.getFields()[i].set(body,dates[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }

}
