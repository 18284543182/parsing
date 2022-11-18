package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 10:20
 * @Description: 空间天气检测 45DF 45天F10.7厘米通量 参考文件 空间天气监测\20221104\45DF\110345DF.txt
 */
@Data
public class SpaceWeatherMonitoringFluxForecast extends Message{

    /**
     * 时间
     */
    public String time;


    /**
     * F10.7厘米通量值
     */
    public String value;
}
