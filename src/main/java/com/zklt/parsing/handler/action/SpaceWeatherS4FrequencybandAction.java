package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherS4Frequencyband;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author xiejian
 * @date 2022/11/24 10:26
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherS4Frequencyband", getAction = SpaceWeatherS4Frequencyband.class)
public class SpaceWeatherS4FrequencybandAction implements MessageAction<SpaceWeatherS4Frequencyband> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherS4Frequencyband> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public  HandlerMessage<SpaceWeatherS4Frequencyband> decode(String data, Class<SpaceWeatherS4Frequencyband> clazz) throws InstantiationException, IllegalAccessException {
        data = data.trim().replaceAll(" +", " ");
        String[] dates = data.split("\\s+");
        Field[] files = clazz.getFields();
        if (dates.length!=files.length){
            return null;
        }
        HandlerMessage<SpaceWeatherS4Frequencyband> resMessage = new HandlerMessage<>();
        SpaceWeatherS4Frequencyband body = clazz.newInstance();
        for (int i= 0;i<files.length;i++){
            Field field = files[i];
            field.setAccessible(true);
            field.set(body,dates[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }
}
