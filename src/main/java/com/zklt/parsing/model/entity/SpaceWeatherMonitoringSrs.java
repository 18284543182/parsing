package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2022/11/19 21:43
 * @Description: 空间天气监测\20221105\SRS(太阳活动区参数）
 */
@Data
public class SpaceWeatherMonitoringSrs extends Message{

    /**
     * 活动区编号
     */
    public String nmbr;

    /**
     * 位置
     */
    public String location;

    public String Lo;

    /**
     * 区域
     */
    public String Area;

    public String Z;
    public String LL;
    public String NN;
    /**
     * 磁圈类型
     */
    public String MagType;

    /**
     * I:Regions with Suaspts 有黑细活动区
     * IA:Halpha piageswithowt Spots 天黑了Hapha光斑
     * II.regions Due to ker 回归性活动区
     */
    private String ident;


}
