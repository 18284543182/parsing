package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 *空间天气检测 ACE文件  高能太阳质子的实时积分通量  参考文件名 202211_ace_sis_1h
 */

@Data
public class SpaceWeatherMonitoringAceSis {

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
     *积分质子通量 > 10 MeV
     */
    private String integralMev10;

    /**
     *积分质子通量 > 30 MeV
     */
    private String integralMev30;


}
