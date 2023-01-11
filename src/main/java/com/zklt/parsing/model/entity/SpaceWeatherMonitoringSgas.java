package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2023/1/11 17:27
 * @Description: 太阳活动F10.7指数（用SGAS和太阳黑子数（用SGAS）  目录：空间天气监测\20221105\SGAS
 */
@Data
public class SpaceWeatherMonitoringSgas extends Message{
    public String date;

    public String time;

    public String f107;

    public String sn;

}
