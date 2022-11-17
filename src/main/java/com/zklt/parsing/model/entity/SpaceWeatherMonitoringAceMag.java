package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;
/**
 *空间天气检测 ACE文件  行星间磁场值  参考文件名 202211_ace_mag_1h
 */

@Data
public class SpaceWeatherMonitoringAceMag {

    /**
     * 记录id
     */
    private String dRecordId;

    /**
     * 记录时间
     */
    private Date dIymdhm;

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
    private String s;

    /**
     *
     */
    private String bx;

    /**
     *
     */
    private String by;

    /**
     *
     */
    private String bz;

    /**
     *
     */
    private String bt;

    /**
     *纬度
     */
    private String lat;

    /**
     *经度
     */
    private String lon;

}
