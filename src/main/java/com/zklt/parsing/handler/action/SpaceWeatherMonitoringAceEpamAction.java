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
@Mapper(type = "Epam", getAction = SpaceWeatherMonitoringAceEpam.class)
public class SpaceWeatherMonitoringAceEpamAction implements MessageAction<SpaceWeatherMonitoringAceEpam> {
    @Override
    public String doAction(HandlerMessage<SpaceWeatherMonitoringAceEpam> message) {
        SpaceWeatherMonitoringAceEpam spaceWeatherMonitoringAceEpam = new SpaceWeatherMonitoringAceEpam();
        return JSON.toJSONString(spaceWeatherMonitoringAceEpam);
    }
}