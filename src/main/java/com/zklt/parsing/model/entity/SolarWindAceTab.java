package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/24 11:44
 * @Description: 空间天气要素\行星际空间太阳风等离子体\ACE卫星\太阳风速度密度温度
 */
@Data
public class SolarWindAceTab extends Message{


    public String date;

    public String time;


    public String np;


    public String vp;


    public String tpr;


    public String alphaRatio;


    public String vGse;


    public String vRtn;


    public String vGsm;


    public String scPosGse;


    public String scPosGsm;
}
