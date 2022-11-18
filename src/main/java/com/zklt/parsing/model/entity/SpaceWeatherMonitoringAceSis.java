package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 *空间天气检测 ACE文件  高能太阳质子的实时积分通量  参考文件名 202211_ace_sis_1h
 */

@Data
public class SpaceWeatherMonitoringAceSis extends Message{

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
    public String s1;

    /**
     *积分质子通量 > 10 MeV
     */
    public String integralMev10;

    /**
     *状态
     */
    public String s2;

    /**
     *积分质子通量 > 30 MeV
     */
    public String integralMev30;


}
