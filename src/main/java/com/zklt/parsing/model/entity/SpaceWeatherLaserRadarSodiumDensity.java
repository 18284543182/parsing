package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/23 10:27
 * @Description: 空间天气1122\钠层密度  激光雷达钠层密度数据 参考文件 HKT_LID01_DNA_L21_01D_20221022000000.DAT
 */
@Data
public class SpaceWeatherLaserRadarSodiumDensity extends Message{

    public String date;

    public String time;

    /**
     * 台站设备标识
     */
    public String stationCode;
    /**
     * 高度
     */
    public String altitude;

    /**
     * 钠原子密度
     */
    public String sodiumDensity;

}
