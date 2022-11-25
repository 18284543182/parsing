package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 21:44
 * @Description:
 */
@Data
public class SpaceWeatherIonosoudes extends Message{

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
