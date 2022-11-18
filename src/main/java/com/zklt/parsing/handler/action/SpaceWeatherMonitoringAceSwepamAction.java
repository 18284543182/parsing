package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceSwepam;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

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
}
