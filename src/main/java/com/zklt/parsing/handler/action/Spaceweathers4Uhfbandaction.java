package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherS4Uhfband;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author xiejian
 * @date 2022/11/24 10:26
 * @Description:
 */
@Service
@Mapper(type = "Spaceweathers4Uhfband", getAction = SpaceWeatherS4Uhfband.class)
public class Spaceweathers4Uhfbandaction implements MessageAction<SpaceWeatherS4Uhfband> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherS4Uhfband> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public  HandlerMessage<SpaceWeatherS4Uhfband> decode(String data, Class<SpaceWeatherS4Uhfband> clazz) throws InstantiationException, IllegalAccessException {
        data = data.trim().replaceAll(" +", " ");
        String[] dates = data.split("\\s+");
        Field[] files = clazz.getFields();
        if (dates.length!=files.length){
            return null;
        }
        HandlerMessage<SpaceWeatherS4Uhfband> resMessage = new HandlerMessage<>();
        SpaceWeatherS4Uhfband body = clazz.newInstance();
        for (int i= 0;i<files.length;i++){
            Field field = files[i];
            field.setAccessible(true);
            field.set(body,dates[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }
}
