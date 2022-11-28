package com.zklt.parsing.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author xiejian
 * @date 2022/11/28 21:44
 * @Description:
 */
@Data
public class SpaceWeatherXraysJsonDataTab extends Message{

    /**
     * 台站代码
     */
    private Date time_tag;

    private String flux;

    private String satellite;

    private String observed_flux;

    private String electron_correction;

    private String electron_contaminaton;

    private String energy;
}
