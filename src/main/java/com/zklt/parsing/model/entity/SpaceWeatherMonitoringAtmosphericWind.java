package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 *
 * @author xiejian
 * @date 2022/11/21 11:46
 * @Description:  空间天气1119\激光雷达\大气风场\zwdata.20221119123921.12405
 */
@Data
public class SpaceWeatherMonitoringAtmosphericWind extends Message{

    /**
     * 台站编码
     */
    public String stationCode;

    /**
     * 设备类型编码与设备序号
     */
    public String deviceCode;

    /**
     * 观测地点
     */
    public String observeAddr;

    /**
     * 纬度
     */
    public String latitude;

    /**
     * 经度
     */
    public String longitude;

    /**
     * 发生时间：格式YYYYMMDDHHMMSS
     */
    public String happenTime;

    /**
     * 高度
     */
    public String altitude;

    /**
     * 水平风场
     */
    public String velocity;

    /**
     * 水平风场方位角
     */
    public String direction;







}
