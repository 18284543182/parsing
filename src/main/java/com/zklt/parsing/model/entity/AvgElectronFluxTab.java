package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wurui
 * @date 2022/11/23 14:25
 * @Description: 空间天气要素\磁层粒子辐射\GOES17\5min电子通量
 */
@Data
public class AvgElectronFluxTab extends Message{




    /**
     * 0-  50-4000千电子伏微分电子通量平均值
     * 1-  微分电子通量随机误差和系统误差积分值
     * 2-  大于2兆电子伏高能电子积分通量5分钟平均值
     * 3-  大于2兆电子伏高能电子通量随机误差和系统误差积分值
     *
     */
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



}
