package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/28 21:04
 * @Description: 空间天气指数历史数据\1-Minute AE指数  每分钟的AE指数
 */
@Data
public class SpaceWeatherMonitoringOneMinuteAe extends Message{
    public String date;

    public String time;

    /**
     * 标识符
     */
    public String identifier;


    /**
     * 版本号
     */
    public String editionNumber;

    /**
     * AE指数
     */
    public String value;

    /**
     * 每小时平均值
     */
    public String meanvalue;



}
