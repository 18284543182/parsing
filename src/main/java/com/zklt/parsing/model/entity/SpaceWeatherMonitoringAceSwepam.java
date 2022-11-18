package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/17 21:58
 * @Description: 空间天气检测 ACE文件 太阳风等离子体的实时体积参数 参考文件 202211_ace_swepam_1h.txt
 */
@Data
public class SpaceWeatherMonitoringAceSwepam extends Message{

    /**
     * 年
     */
    public String year;

    /**
     * 月
     */
    public String month;

    /**
     * 日
     */
    public String day;

    /**
     * 时分 数据是合在一起的
     */
    public String hhmm;

    /**
     *
     */
    public String day1;

    /**
     *
     */
    public String day2;

    /**
     *状态
     */
    public String s;

    /**
     *密度
     */
    public String density;

    /**
     *速度
     */
    public String speed;

    /**
     *温度
     */
    public String temperature;
}
