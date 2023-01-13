package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 12:48
 * @Description: 行星际空间太阳风等离子体\OMNI数据
 */
@Data
public class SolarWindPlasmaOmnlTab extends Message{


    /**
     * 年
     */
    public String year;

    /**
     * 天数
     */
    public String day;

    /**
     * 小时
     */
    public String hour;

    /**
     * 分
     */
    public String minute;

    /**
     * IMF航天器I3的ID
     */
    public String imfId;

    /**
     * SW等离子飞船I3的ID
     */
    public String swId;

    /**
     * 国际货币基金组织平均值的积分I4
     */
    public String pointsInImfAverages;

    /**
     * Plasma平均值I4中的点数
     */
    public String pointsInPlasmaAverages;

    /**
     * 插入百分比
     */
    public String percentInterp;

    /**
     * 时移，秒
     */
    public String timeshiftSec;

    /**
     * RMS，时移
     */
    public String rmsTimeshift;

    /**
     * RMS，相前正常值
     */
    public String rmsPhaseFrontNormal;

    /**
     * 记录秒
     */
    public String sec;

    /**
     * 场强平均值
     */
    public String fieldMagnitudeAverage;

    /**
     * BxnT
     */
    public String bxNt;

    /**
     * BynT
     */
    public String byNt;

    /**
     * BznT
     */
    public String bzNt;

    /**
     * BynT根据换班后GSE组件确定
     */
    public String byNtDetermined;

    /**
     * BznT根据换班后GSE组件确定
     */
    public String bzNtDetermined;

    /**
     * RMS SD B标量
     */
    public String rmsSdBScalar;

    /**
     * RMS SD场矢量
     */
    public String rmsSdFieldVector;

    /**
     * 流速，km/h
     */
    public String flowSpeed;

    /**
     * Vx速度，km/h
     */
    public String vxVelocity;

    /**
     * Vy速度，km/h
     */
    public String vyVelocity;

    /**
     * Vz速度，km/h
     */
    public String vzVelocity;

    /**
     * 质子密度，n/cc
     */
    public String protonDensity;

    /**
     * 温度
     */
    public String temperature;

    /**
     * 流量压力nPa
     */
    public String flowPressure;

    /**
     * 电场，mV/m
     */
    public String electricField;

    /**
     * 血浆βF7.2
     */
    public String plasmaBeta;

    /**
     * 阿尔芬马赫数
     */
    public String alfvenMachNumber;

    /**
     * X（s/c）、GSE、Re
     */
    public String x;

    /**
     * y（s/c）、GSE、Re
     */
    public String y;

    /**
     * z（s/c）、GSE、Re
     */
    public String z;

    /**
     * BSN位置，Xgse
     */
    public String bsnLocationXgse;

    /**
     * BSN位置，Ygse
     */
    public String bsnLocationYgse;

    /**
     * BSN位置，zgse
     */
    public String bsnLocationZgse;

    /**
     * AE指数
     */
    public String aeIndex;

    /**
     * AL指数
     */
    public String alIndex;


    /**
     * Au指数
     */
    public String auIndex;

    /**
     * SYM/D指数
     */
    public String symDIndex;

    /**
     * SYM/H指数
     */
    public String symHIndex;

    /**
     * ASY/D指数
     */
    public String asyDIndex;

    /**
     * ASY/H指数
     */
    public String asyHIndex;

    /**
     * PC（N）索引
     */

    public String pcNIndex;

    /**
     * 磁共振马赫数
     */

    public String magnetosonicMachNumber;

    /**
     * Proton Flux >10 MeV, 1/(cm**2-sec-ster)  F9.2 See  footnote J below
     */
    public String no1;

    /**
     * Proton Flux >30 MeV, 1/(cm**2-sec-ster)  F9.2
     */
    public String no2;

    /**
     * Proton Flux >60 MeV, 1/(cm**2-sec-ster)  F9.2
     */
    public String no3;

}
