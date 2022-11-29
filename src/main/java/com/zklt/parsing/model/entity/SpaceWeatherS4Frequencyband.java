package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2022/11/24 10:23
 * @Description: 空间天气1122\单站实时-S4\L频段
 */
@Data
public class SpaceWeatherS4Frequencyband extends Message{


    /**
     * 站点编号
     */
    public String stationCode;

    /**
     * 年月日
     */
    public String fisrtDate;

    /**
     * 时分秒
     */
    public String lastDate;

    /**
     * 卫星编号
     */
    public String satelliteCode;

    /**
     * 仰角
     */
    public String elevation;

    /**
     * 方位角
     */
    public String azimuth;

    /**
     * S4_L
     */
    public String s4l;

}
