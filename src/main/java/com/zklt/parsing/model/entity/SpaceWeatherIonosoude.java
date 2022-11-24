package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 20:34
 * @Description:  空间天气1122\单站实时-foF2、电离层最高可用频率 	电离层垂测数据
 */
@Data
public class SpaceWeatherIonosoude extends Message{

    /**
     * 台站代码
     */
    public String stationCode;

    public String date;

    public String time;

    public String fof2;

    public String m3000;

    public String fmin;

    public String value;
}
