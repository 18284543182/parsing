package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringFluxForecast;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 12:01
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringFluxForecast", getAction = SpaceWeatherMonitoringFluxForecast.class)
public class SpaceWeatherMonitoringFluxForecastAction implements MessageAction<SpaceWeatherMonitoringFluxForecast> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringFluxForecast> message) {
        return message.getMessage();
    }
}
