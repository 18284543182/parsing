package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/28 18:18
 * @Description: Kp-Ap
 */
@Data
public class KpapApSNTab extends Message{


    public String year;

    public String month;

    public String day;

    /**
     * 自1932-01-01 00:00 UT至UT日开始的天数
     */
    public String days;

    /**
     * 是自1932-101-01 00.00 UT至UT日中午的天数
     */
    public String daysM;

    /**
     * 巴特尔斯太阳自转数
     */
    public String bsr;

    /**
     * 是BSR内的日
     */
    public String db;

    /**
     * 超声波检测第一天的Kp  以此类推
     */
    public String kp1;



    public String kp2;


    public String kp3;


    public String kp4;


    public String kp5;


    public String kp6;


    public String kp7;


    public String kp8;


    /**
     * 超声波第一天  以此类推
     */

    public String ap1;


    public String ap2;


    public String ap3;


    public String ap4;


    public String ap5;


    public String ap6;


    public String ap7;


    public String ap8;


    public String ap;


    public String sn;



    public String f107obs;


    public String f107adj;


    public String d;

}
