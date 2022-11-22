package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author wurui
 * @date 2022/11/22 15:20
 * @Description: 气象数据 浮标 xml格式 参考文件 202209140630MF14006.dat.xml
 */
@Data
public class MeteorologicalBuoy extends Message{

    public String year;

    public String month;

    public String day;

    public String hour;

    public String minute;

    public String second;

    public String id;

    public String type;

    public String name;

    public String noinfo;

    public String kind;

    public String longitude;

    public String latitude;

    public String ws;

    public String wd;

    public String wsm;

    public String at;

    public String bp;

    public String hu;

    public String wt;

    public String bg;

    public String bx;

    public String zq;

    public String ybg;

    public String yzq;

    public String tenthbg;

    public String tenthzq;

    public String zbg;

    public String zzq;

    public String cs;

    public String cd;

    public String se;

    public String no;


}
