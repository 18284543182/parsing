package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 21:01
 * @Description: 空间天气1122\电离层电子密度剖面
 */
@Data
public class SpaceWeatherElectronDensity extends Message{
    public String date;

    public String time;

    public String filename;
}
