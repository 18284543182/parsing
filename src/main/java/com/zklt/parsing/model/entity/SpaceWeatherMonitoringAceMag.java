package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;
/**
 *空间天气检测 ACE文件  行星间磁场值  参考文件名 202211_ace_mag_1h
 */

@Data
public class SpaceWeatherMonitoringAceMag extends Message {

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
     *
     */
    public String bx;

    /**
     *
     */
    public String by;

    /**
     *
     */
    public String bz;

    /**
     *
     */
    public String bt;

    /**
     *纬度
     */
    public String lat;

    /**
     *经度
     */
    public String lon;

}
