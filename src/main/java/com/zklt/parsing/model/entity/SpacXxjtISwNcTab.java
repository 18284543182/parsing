package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2023/01/13 18:25
 * @Description: oe_f1m_dscovr_s20170103000000_e20170103235959_p20170104022116_pub.nc
 */
@Data
public class SpacXxjtISwNcTab extends Message {

    /**
     *  太阳风速度
     */
    public String vSwSpeed;

    /**
     * 太阳风密度
     */
    public String vSwDensity;

    /**
     *  太阳风温度
     */
    public String vSwTemp;
    /**
     * 资料时间
     */
    public String dateTime;
}
