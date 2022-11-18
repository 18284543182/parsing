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
    public String middleLatitudeA;

    /**
     * 中纬度 第一个K指数
     */
    public String middleLatitudeKindice1;

    /**
     * 中纬度 第二个K指数
     */
    public String middleLatitudeKindice2;

    /**
     * 中纬度 第3个K指数
     */
    public String middleLatitudeKindice3;

    /**
     * 中纬度 第4个K指数
     */
    public String middleLatitudeKindice4;

    /**
     * 中纬度 第5个K指数
     */
    public String middleLatitudeKindice5;

    /**
     * 中纬度 第6个K指数
     */
    public String middleLatitudeKindice6;

    /**
     * 中纬度 第7个K指数
     */
    public String middleLatitudeKindice7;

    /**
     * 中纬度 第8个K指数
     */
    public String middleLatitudeKindice8;

    /**
     * 高纬度 A指数
     */
    public String highLatitudeA;

    /**
     * 高纬度 第1个K指数
     */
    public String highLatitudeKindice1;

    /**
     * 高纬度 2个K指数
     */
    public String highLatitudeKindice2;

    /**
     * 高纬度 第3个K指数
     */
    public String highLatitudeKindice3;

    /**
     * 高纬度 第4个K指数
     */
    public String highLatitudeKindice4;

    /**
     * 高纬度 第5个K指数
     */
    public String highLatitudeKindice5;

    /**
     * 高纬度 第6个K指数
     */
    public String highLatitudeKindice6;

    /**
     * 高纬度 第7个K指数
     */
    public String highLatitudeKindice7;

    /**
     * 高纬度 第8个K指数
     */
    public String highLatitudeKindice8;

    /**
     * 估计值 A指数
     */
    public String hestimatedA;


    /**
     * 估计值 第1个k指数
     */
    public String hestimatedKindice1;

    /**
     * 估计值 第2个k指数
     */
    public String hestimatedKindice2;

    /**
     * 估计值 第3个k指数
     */
    public String hestimatedKindice3;

    /**
     * 估计值 第4个k指数
     */
    public String hestimatedKindice4;

    /**
     * 估计值 第5个k指数
     */
    public String hestimatedKindice5;

    /**
     * 估计值 第6个k指数
     */
    public String hestimatedKindice6;

    /**
     * 估计值 第7个k指数
     */
    public String hestimatedKindice7;

    /**
     * 估计值 第8个k指数
     */
    public String hestimatedKindice8;





}
