package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/28 16:23
 * @Description: 互联网\Dscovr  行星际等离子体的密度、速度 和温度
 */
@Data
public class SpaceWeatherMonitoringDscovrPlasma extends Message{
    /**
     *
     */
    public String date;

    /**
     *
     */
    public String time;


    /**
     * 密度
     */
    public String density;

    /**
     * 速度
     */
    public String speed;

    /**
     * 温度
     */
    public String temperature;


}
