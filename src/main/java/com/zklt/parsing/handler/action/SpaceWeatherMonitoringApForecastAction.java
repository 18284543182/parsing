package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringApForecast;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 11:57
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringApForecast", getAction = SpaceWeatherMonitoringApForecast.class)
public class SpaceWeatherMonitoringApForecastAction implements MessageAction<SpaceWeatherMonitoringApForecast> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringApForecast> message) {
        return message.getMessage();
    }
}