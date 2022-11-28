package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/28 20:09
 * @Description: 空间天气指数历史数据\Dst指数  历史的dst指数
 */
@Data
public class SpaceWeatherMonitoringHistoryDst extends Message{

    public String date;

    public String time;

    public String dst;

    public String type;

    /**
     * 基值
     */
    public String basevalue;

    /**
     * 每天平均值
     */
    public String meanvalue;

}
