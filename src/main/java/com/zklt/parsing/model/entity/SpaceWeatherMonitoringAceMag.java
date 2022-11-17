package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

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
     *电子状态
     */
    private String electronS;

}
