package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 11:28
 * @Description: 空间天气要素\太阳大气\GOES17 X射线通量
 */
@Data
public class SolarAtmosphereXrayFluxTab extends Message{


    public String date;

    public String time;


    /**
     * 通道1
     */
    public String xrsa1Flux;

    /**
     * 通道2
     */
    public String xrsa2Flux;

}
