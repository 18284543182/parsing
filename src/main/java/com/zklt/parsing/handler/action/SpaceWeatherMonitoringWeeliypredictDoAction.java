package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringWeeliypredictDo;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 12:02
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringWeeliypredictDo", getAction = SpaceWeatherMonitoringWeeliypredictDo.class)
public class SpaceWeatherMonitoringWeeliypredictDoAction implements MessageAction<SpaceWeatherMonitoringWeeliypredictDo> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringWeeliypredictDo> message) {
        return message.getMessage();
    }
}
