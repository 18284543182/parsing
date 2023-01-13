package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 15:24
 * @Description: 空间天气区域现报-S4
 */
@Data
public class SpaceWeatherRegionNowNcasts4 extends Message{
    public String date;

    public String time;

    /**
     * 起始纬度
     */
    public String lats;
    /**
     * 终止纬度
     */
    public String late;
    /**
     * 纬度间隔
     */
    public String latstep;
    /**
     * 起始经度
     */
    public String lons;
    /**
     * 终止经度
     */
    public String lone;
    /**
     * 经度间隔
     */
    public String lonstep;

    public String latitude;

    public String longitude;


    public String value1;

}
