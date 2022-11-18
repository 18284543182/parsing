package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 10:32
 * @Description: 空间天气检测\DGD_DSD_DPD  地磁数据DGD 参考文件 20221103_DGD.txt
 */
@Data
public class SpaceWeatherMonitoringDgd extends Message{

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
     * 中纬度A指数
     */
    public String MiddleLatitudeA;

    /**
     * 中纬度 第一个K指数
     */
    public String MiddleLatitudeKindice1;

    /**
     * 中纬度 第二个K指数
     */
    public String MiddleLatitudeKindice2;

    /**
     * 中纬度 第3个K指数
     */
    public String MiddleLatitudeKindice3;

    /**
     * 中纬度 第4个K指数
     */
    public String MiddleLatitudeKindice4;

    /**
     * 中纬度 第5个K指数
     */
    public String MiddleLatitudeKindice5;

    /**
     * 中纬度 第6个K指数
     */
    public String MiddleLatitudeKindice6;

    /**
     * 中纬度 第7个K指数
     */
    public String MiddleLatitudeKindice7;

    /**
     * 中纬度 第8个K指数
     */
    public String MiddleLatitudeKindice8;

    /**
     * 高纬度 A指数
     */
    public String HighLatitudeA;

    /**
     * 高纬度 第1个K指数
     */
    public String HighLatitudeKindice1;

    /**
     * 高纬度 2个K指数
     */
    public String HighLatitudeKindice2;

    /**
     * 高纬度 第3个K指数
     */
    public String HighLatitudeKindice3;

    /**
     * 高纬度 第4个K指数
     */
    public String HighLatitudeKindice4;

    /**
     * 高纬度 第5个K指数
     */
    public String HighLatitudeKindice5;

    /**
     * 高纬度 第6个K指数
     */
    public String HighLatitudeKindice6;

    /**
     * 高纬度 第7个K指数
     */
    public String HighLatitudeKindice7;

    /**
     * 高纬度 第8个K指数
     */
    public String HighLatitudeKindice8;

    /**
     * 估计值 A指数
     */
    public String HestimatedA;


    /**
     * 估计值 第1个k指数
     */
    public String HestimatedKindice1;

    /**
     * 估计值 第2个k指数
     */
    public String HestimatedKindice2;

    /**
     * 估计值 第3个k指数
     */
    public String HestimatedKindice3;

    /**
     * 估计值 第4个k指数
     */
    public String HestimatedKindice4;

    /**
     * 估计值 第5个k指数
     */
    public String HestimatedKindice5;

    /**
     * 估计值 第6个k指数
     */
    public String HestimatedKindice6;

    /**
     * 估计值 第7个k指数
     */
    public String HestimatedKindice7;

    /**
     * 估计值 第8个k指数
     */
    public String HestimatedKindice8;





}
