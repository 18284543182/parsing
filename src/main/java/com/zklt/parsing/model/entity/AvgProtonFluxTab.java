package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 10:41
 * @Description: 空间天气要素\磁层粒子辐射\GOES16\5min质子通量
 */
@Data
public class AvgProtonFluxTab extends Message{

    public String ident;

    public String date;

    public String time;

    /**
     * 通道1
     */
    public String channel1;

    /**
     * 通道2
     */
    public String channel2;

    /**
     * 通道3
     */
    public String channel3;

    /**
     * 通道4
     */
    public String channel4;

    /**
     * 通道5
     */
    public String channel5;
    /**
     * 通道6
     */
    public String channel6;
    /**
     * 通道7
     */
    public String channel7;
    /**
     * 通道8
     */
    public String channel8;
    /**
     * 通道9
     */
    public String channel9;
    /**
     * 通道10
     */
    public String channel10;
    /**
     * 通道11
     */
    public String channel11;
    /**
     * 通道12
     */
    public String channel12;
    /**
     * 通道13
     */
    public String channel13;

}
