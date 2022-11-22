package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherLaserRadarAtmTem;
import com.zklt.parsing.model.entity.SpaceWeatherLonosphericTecScintillation;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/22 14:06
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherLonosphericTecScintillation", getAction = SpaceWeatherLonosphericTecScintillation.class)
public class SpaceWeatherLonosphericTecScintillationAction implements MessageAction<SpaceWeatherLonosphericTecScintillation> {

    @Override
    public Object doAction(HandlerMessage<SpaceWeatherLonosphericTecScintillation> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

}
