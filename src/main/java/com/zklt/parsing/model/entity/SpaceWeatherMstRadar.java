package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/21 10:03
 * @Description: 空间天气1119 MST雷达数据
 */
@Data
public class SpaceWeatherMstRadar extends Message{
    public String date;

    public String time;

    /**
     * 台站代码
     */
    public String stationCode;

    /**
     * 设备号
     */
    public String instrumentId;

    /**
     * 高度
     */
    public String alt;

    /**
     * 风向
     */
    public String d;

    /**
     * 水平风速
     */
    public String s;

    /**
     * 垂直风速
     */
    public String vw;

    /**
     * 大气折射指数结构
     * 常数
     */
    public String cn2;

}
