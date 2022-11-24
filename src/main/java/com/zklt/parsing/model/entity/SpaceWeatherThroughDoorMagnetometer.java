package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 16:32
 * @Description: 通门式磁力计 地磁场变化磁场 H、Z、D 三分量与温度 T
 */
@Data
public class SpaceWeatherThroughDoorMagnetometer extends Message{




    /**
     * 台站代码
     */
    public String stationCode;

    /**
     * 设备号
     */
    public String instrumentId;

    /**
     * 采样率
     */
    public String samplingRate;

    /**
     * 通道数
     */
    public String channel;

    /**
     * H 分量测项代码
     */
    public String hComponentCode;

    /**
     * Z 分量测项代码
     */
    public String zComponentCode;


    /**
     * D 分量测项代码
     */
    public String dComponentCode;
    /**
     * 温 度 测 项代码
     */
    public String tComponentCode;

    public String date;

    public String time;

    /**
     * H 分量值
     */
    public String hComponent;
    /**
     * Z 分量值
     */
    public String zComponent;

    /**
     * D 分量值
     */
    public String dComponent;

    /**
     *温度值
     */
    public String tComponent;


}
