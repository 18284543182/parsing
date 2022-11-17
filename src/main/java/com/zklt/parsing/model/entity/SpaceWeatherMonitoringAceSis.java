package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 *空间天气检测 ACE文件  高能太阳质子的实时积分通量  参考文件名 202211_ace_sis_1h
 */

@Data
public class SpaceWeatherMonitoringAceSis {

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 时分 数据是合在一起的
     */
    private String hhmm;

    /**
     *
     */
    private String day1;

    /**
     *
     */
    private String day2;

    /**
     *状态
     */
    private String s1;

    /**
     *积分质子通量 > 10 MeV
     */
    private String integralMev10;

    /**
     *状态
     */
    private String s2;

    /**
     *积分质子通量 > 30 MeV
     */
    private String integralMev30;


}
