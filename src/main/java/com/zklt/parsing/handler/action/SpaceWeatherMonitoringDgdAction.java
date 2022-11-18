package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDgd;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

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
        return message.getMessage();
    }
}
