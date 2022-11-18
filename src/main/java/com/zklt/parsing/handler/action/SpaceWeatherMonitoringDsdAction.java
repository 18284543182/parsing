package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDsd;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 12:00
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringDsd", getAction = SpaceWeatherMonitoringDsd.class)
public class SpaceWeatherMonitoringDsdAction implements MessageAction<SpaceWeatherMonitoringDsd> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringDsd> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }
}
