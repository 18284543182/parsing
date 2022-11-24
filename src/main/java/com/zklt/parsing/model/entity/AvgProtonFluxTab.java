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

}
