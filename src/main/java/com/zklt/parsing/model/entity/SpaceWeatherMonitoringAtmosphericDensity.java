package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 *
 * @author xiejian
 * @date 2022/11/21 11:46
 * @Description:  空间天气1119\激光雷达\激光雷达-大气密度\zwdata.20221119123921.12405
 */
@Data
public class SpaceWeatherMonitoringAtmosphericDensity extends Message{

    /**
     * 台站编码
     */
    public String stationCode;

    /**
     * 设备类型编码与设备序号
     */
    public String deviceCode;


    /**
     * 发生时间：格式YYYYMMDDHHMMSS
     */
    public String happenTime;

    /**
     * 高度
     */
    public String altitude;

    /**
     * 大气密度
     */
    public String density;




}
