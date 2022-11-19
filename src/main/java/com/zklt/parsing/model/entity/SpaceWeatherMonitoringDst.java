package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/19 21:37
 * @Description:  空间天气监测\空间天气监测要素样例数据\Dst指数 地磁Dst指数 参考文件 Dst index.txt
 */
@Data
public class SpaceWeatherMonitoringDst extends Message{

    public String date;

    public String time;

    public String dst;



}
