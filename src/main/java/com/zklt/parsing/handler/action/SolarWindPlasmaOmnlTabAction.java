package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SolarWindAceTab;
import com.zklt.parsing.model.entity.SolarWindPlasmaOmnlTab;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/24 12:52
 * @Description:
 */
@Service
@Mapper(type = "SolarWindPlasmaOmnlTab", getAction = SolarWindPlasmaOmnlTab.class)
public class SolarWindPlasmaOmnlTabAction implements MessageAction<SolarWindPlasmaOmnlTab> {
    @Override
    public Object doAction(HandlerMessage<SolarWindPlasmaOmnlTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }
}
