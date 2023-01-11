package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;
/**
 *空间天气检测 ACE文件  行星间磁场值  参考文件名 202211_ace_mag_1h
 */

@Data
public class SpaceWeatherMonitoringAceMag extends Message {


    /**
     *
     */
    public String date;

    /**
     *
     */
    public String time;


    /**
     *
     */
    public String bxGsm;

    /**
     *
     */
    public String byGsm;

    /**
     *
     */
    public String bzGsm;

    /**
     *
     */
    public String lonGsm;

    /**
     *
     */
    public String latGsm;

    /**
     *总磁场
     */
    public String bt;

}
