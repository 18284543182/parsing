package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wurui
 * @date 2022/11/26 16:16
 * @Description:
 */
@Data
public class SpaceWeatherHighEnergyElectron {
    public Date time_tag;

    public String satellite;

    public String flux;

    public String energy;
}
