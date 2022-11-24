package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 20:02
 * @Description: 空间天气1122\radio_shidao-太阳射电流量
 */
@Data
public class SpaceWeatherRadioShidao extends Message{
    public String date;

    public String time;

    public String value1;
    public String value2;
    public String value3;
    public String value4;
    public String value5;


}
