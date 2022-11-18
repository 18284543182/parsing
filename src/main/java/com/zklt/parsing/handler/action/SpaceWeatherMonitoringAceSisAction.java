package com.zklt.parsing.handler.action;


import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceSis;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/18 11:54
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringAceSis", getAction = SpaceWeatherMonitoringAceSis.class)
public class SpaceWeatherMonitoringAceSisAction implements MessageAction<SpaceWeatherMonitoringAceSis> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringAceSis> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }
}
