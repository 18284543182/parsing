package com.zklt.parsing.handler.action;

import com.alibaba.fastjson.JSON;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAceMag;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wurui
 * @date 2022/11/17 18:26
 * @Description:
 */
@Service
@Mapper(type = "Mag", getAction = SpaceWeatherMonitoringAceMag.class)
public class SpaceWeatherMonitoringAceMagAction implements MessageAction<SpaceWeatherMonitoringAceMag> {

    @Override
    public String doAction(HandlerMessage<SpaceWeatherMonitoringAceMag> message) {
        SpaceWeatherMonitoringAceMag spaceWeatherMonitoringAceMag=message.getMessage();
        return JSON.toJSONString(spaceWeatherMonitoringAceMag);
    }
}
