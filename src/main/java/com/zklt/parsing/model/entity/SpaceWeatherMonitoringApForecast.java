package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 10:16
 * @Description: 空间天气检测 45DF 45天AP预测45DF 参考文件 空间天气监测\20221104\45DF\110345DF.txt
 */
@Data
public class SpaceWeatherMonitoringApForecast extends Message{

    /**
     * 时间
     */
    public String time;


    /**
     * AP预测值
     */
    public String value;



}
