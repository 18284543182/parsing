package com.zklt.parsing.model.entity;


import com.zklt.parsing.model.entity.Message;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiejian
 * @date 2023/01/13 17:25
 * @Description: oe_m1m_dscovr_s20170101000000_e20170101235959_p20170102021117_pub.nc
 */
@Data
public class SpacXxjtImfTab extends Message {

    /**
     *  磁场总强度
     */
    public String vImfBt;

    /**
     *  GSM坐标系X分量
     */
    public String vImfGsmbx;

    /**
     *  GSM坐标系Y分量
     */
    public String vImfGsmby;
    /**
     *  GSM坐标系Z分量
     */
    public String vImfGsmbz;

    /**
     * 资料时间
     */
    public String dateTime;
}
