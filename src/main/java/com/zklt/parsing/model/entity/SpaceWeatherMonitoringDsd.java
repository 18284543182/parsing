package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 11:04
 * @Description: 空间天气检测\DGD_DSD_DPD 每日太阳数据 参考文件 20221103_DSD.txt
 */
@Data
public class SpaceWeatherMonitoringDsd extends Message{
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
     *
     */
    public String flux;

    /**
     *
     */
    public String sunspotNumber;

    /**
     *
     */
    public String hemis;

    /**
     *
     */
    public String regions;


    /**
     *
     */
    public String meanField;

    /**
     *
     */
    public String bkgdFlux;

    /**
     *
     */
    public String Xrayc;

    /**
     *
     */
    public String Xraym;

    /**
     *
     */
    public String Xrayx;

    /**
     *
     */
    public String Xrays;

    /**
     *
     */
    public String optical1;

    /**
     *
     */
    public String optical2;

    /**
     *
     */
    public String optical3;


}
