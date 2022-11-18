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
     *通量
     */
    public String flux;

    /**
     *太阳黑子数
     */
    public String sunspotNumber;

    /**
     *
     */
    public String hemis;

    /**
     *区域
     */
    public String regions;


    /**
     *平均通量
     */
    public String meanField;

    /**
     *Bkgd通量
     */
    public String bkgdFlux;

    /**
     *
     */
    public String xrayc;

    /**
     *
     */
    public String xraym;

    /**
     *
     */
    public String xrayx;

    /**
     *
     */
    public String xrays;

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
