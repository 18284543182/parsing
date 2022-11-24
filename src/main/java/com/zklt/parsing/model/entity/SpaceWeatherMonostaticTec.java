package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2022/11/24 10:23
 * @Description: 空间天气1122\单站实时-foF2、电离层最高可用频率
 */
@Data
public class SpaceWeatherMonostaticTec extends Message{


    /**
     * 站点编号
     */
    public String stationCode;

    /**
     * 年月日
     */
    public String fisrtDate;

    /**
     * 时分秒
     */
    public String lastDate;

    /**
     * tec
     */
    public String tec;

}
