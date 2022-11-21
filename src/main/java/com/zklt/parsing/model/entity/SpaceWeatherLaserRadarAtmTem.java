package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/21 15:07
 * @Description: 空间天气1119 激光雷达 大气温度数据
 */
@Data
public class SpaceWeatherLaserRadarAtmTem extends Message{
    public String date;

    public String time;

    /**
     * 台站代码
     */
    public String stationCode;

    /**
     * 高度
     */
    public String altitude;

    /**
     * 大气温度
     */
    public String temperature;
}
