package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wurui
 * @date 2022/11/26 16:15
 * @Description: 高能质子
 */
@Data
public class SpaceWeatherHighEnergyProtons {

    public Date time_tag;

    public String flux;

    public String satellite;

    public String energy;

    public String yaw_flip;

    public String channel;
}
