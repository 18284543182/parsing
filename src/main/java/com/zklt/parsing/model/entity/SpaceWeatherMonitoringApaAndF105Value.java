package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author jhzhu
 * @date 2022/11/19 18:46
 * @Description:
 */
@Data
public class SpaceWeatherMonitoringApaAndF105Value extends Message {

    public String date;

    public String value;

    /**
     * 值为ap和cm，用这两个字段来区分业务
     */
    public String type;

}
