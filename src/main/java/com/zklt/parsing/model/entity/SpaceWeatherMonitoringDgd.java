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


    public String hour;

    public String stationcode;

    /**
     * k指数
     */
    public String kindice;




}
