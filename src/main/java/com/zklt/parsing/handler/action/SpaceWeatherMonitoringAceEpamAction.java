package com.zklt.parsing.handler.action;

import com.alibaba.fastjson.JSON;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceEpam;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author jhzhu
 * @date 2022/11/17 17:12
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringAceEpam", getAction = SpaceWeatherMonitoringAceEpam.class)
public class SpaceWeatherMonitoringAceEpamAction implements MessageAction<SpaceWeatherMonitoringAceEpam> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringAceEpam> message) {
        SpaceWeatherMonitoringAceEpam spaceWeatherMonitoringAceEpam = message.getMessage();
        return JSON.toJSONString(spaceWeatherMonitoringAceEpam);
    }
}