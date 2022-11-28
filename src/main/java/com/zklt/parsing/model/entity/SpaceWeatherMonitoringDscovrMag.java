package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/28 16:01
 * @Description:  互联网\Dscovr  总磁场 DSCOVR卫星
 */
@Data
public class SpaceWeatherMonitoringDscovrMag extends Message{

    /**
     *
     */
    public String date;

    /**
     *
     */
    public String time;


    /**
     *
     */
    public String bxGsm;

    /**
     *
     */
    public String byGsm;

    /**
     *
     */
    public String bzGsm;

    /**
     *
     */
    public String lonGsm;

    /**
     *
     */
    public String latGsm;

    /**
     *总磁场
     */
    public String bt;
}
