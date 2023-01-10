package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/22 13:58
 * @Description: 空间天气历史1111新增\电离层TEC和闪烁数据（S4)
 */
@Data
public class SpaceWeatherLonosphericTecScintillation extends Message{

    public String year;

    public String month;

    public String day;

    public String hour;

    public String minute;

    public String second;

    /**
     * 卫星编号
     */
    public String vPrn;

    /**
     * 方位角
     */
    public String vAzimuth;

    /**
     * 仰角
     */
    public String vElevation;

    /**
     * 强度闪烁指数
     */
    public String vS4;

    /**
     * 相位闪烁指数
     */
    public String sigph;

    /**
     * 垂直总电子含量
     */
    public String verticaltec;

}
