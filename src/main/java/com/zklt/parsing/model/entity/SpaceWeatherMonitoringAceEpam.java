package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 空间天气检测 ACE文件 微分电子和质子通量 参考文件名 202211_ace_epam_1h
 */
@Data
public class SpaceWeatherMonitoringAceEpam extends Message {


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
     *电子状态
     */
    public String electronS;

    /**
     *电子38-53
     */
    public String electron1;

    /**
     *电子175-315
     */
    public String electron2;

    /**
     *质子状态
     */
    public String protonS;

    /**
     *质子keV47-68
     */
    public String proton1;

    /**
     *质子keV115-195
     */
    public String proton2;

    /**
     *质子keV310-580
     */
    public String proton3;

    /**
     *质子keV795-1193
     */
    public String proton4;

    /**
     *质子keV1060-1900
     */
    public String proton5;

    /**
     *质子keV1060-1900
     */
    public String index;
}
