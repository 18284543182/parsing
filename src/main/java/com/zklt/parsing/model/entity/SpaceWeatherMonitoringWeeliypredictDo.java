package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 11:35
 * @Description: 空间天气监测\20221104\weeklypredict   27天太空天气展望  参考文件 27DO.txt
 */
@Data
public class SpaceWeatherMonitoringWeeliypredictDo extends Message{
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

    public String RadioFlux;

    public String PlanetaryA;

    public String LargestKp;

}
