package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2023/02/02 09:44
 * @Description: 空间天气1122\单站实时-foF2、电离层最高可用频率\ionosoude_2022110710.dat
 */
@Data
public class SpacDlctScaledTab extends Message {

    public String stationCode;

    public String date;

    public String time;

    public String fof2;

    public String val2;

    public String vM;

    public String val4;

}
