package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonostaticTec;
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
@Mapper(type = "SpaceWeatherMonostaticTec", getAction = SpaceWeatherMonostaticTec.class)
public class SpaceweatherMonostaticTecAction implements MessageAction<SpaceWeatherMonostaticTec> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonostaticTec> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public  HandlerMessage<SpaceWeatherMonostaticTec> decode(String data, Class<SpaceWeatherMonostaticTec> clazz) throws InstantiationException, IllegalAccessException {
        data = data.trim().replaceAll(" +", " ");
        String[] dates = data.split("\\s+");
        Field[] files = clazz.getFields();
        if (dates.length!=files.length){
            return null;
        }
        HandlerMessage<SpaceWeatherMonostaticTec> resMessage = new HandlerMessage<>();
        SpaceWeatherMonostaticTec body = clazz.newInstance();
        for (int i= 0;i<files.length;i++){
            Field field = files[i];
            field.setAccessible(true);
            field.set(body,dates[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }
}
