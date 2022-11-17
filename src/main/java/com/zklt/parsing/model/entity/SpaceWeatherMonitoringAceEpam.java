package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 空间天气检测 ACE文件 微分电子和质子通量 参考文件名 202211_ace_epam_1h
 */
@Data
public class SpaceWeatherMonitoringAceEpam extends Message {

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
     *电子状态
     */
    private String electronS;

    /**
     *电子38-53
     */
    private String electron1;

    /**
     *电子175-315
     */
    private String electron2;

    /**
     *质子状态
     */
    private String protonS;

    /**
     *质子keV47-68
     */
    private String proton1;

    /**
     *质子keV115-195
     */
    private String proton2;

    /**
     *质子keV310-580
     */
    private String proton3;

    /**
     *质子keV795-1193
     */
    private String proton4;

    /**
     *质子keV1060-1900
     */
    private String proton5;

    /**
     *质子keV1060-1900
     */
    private String index;
}
