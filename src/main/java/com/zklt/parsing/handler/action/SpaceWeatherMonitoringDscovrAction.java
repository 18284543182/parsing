package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDscovr;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 11:59
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringDscovr", getAction = SpaceWeatherMonitoringDscovr.class)
public class SpaceWeatherMonitoringDscovrAction implements MessageAction<SpaceWeatherMonitoringDscovr> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringDscovr> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }
}
