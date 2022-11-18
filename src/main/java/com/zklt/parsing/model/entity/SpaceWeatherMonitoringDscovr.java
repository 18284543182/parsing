package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/18 11:24
 * @Description: 空间天气监测\空间天气监测要素样例数据\行星际磁场观测资料（DSCOVR)  参考文件 plasma-1-day.json
 */
@Data
public class SpaceWeatherMonitoringDscovr extends Message{

    public String time_tag;

    public String density;

    public String speed;

    public String temperature;
}
