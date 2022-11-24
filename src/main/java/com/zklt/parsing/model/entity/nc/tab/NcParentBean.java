package com.zklt.parsing.model.entity.nc.tab;


import java.util.Date;


/**
 * 记录共有属性实体
 *
 */
public class NcParentBean {

    /**
     * 顶层文件名
     */
    private String topFileName;

    /**
     * 类型文件名
     */
    private String typeFileName;

    /**
     * 卫星名称（美国noaa的GOES卫星）
     */
    private String goesSatelliteName;

    /**
     * 5min 类型文件名(例:电子通量、质子通量)
     */
    private String fiveMinFileName;

    /**
     * 年份
     */
    private String year;

    /**
     * nc文件名
     */
    private String ncFileName;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 0-  50-4000千电子伏微分电子通量平均值
     * 1-  微分电子通量随机误差和系统误差积分值
     * 2-  大于2兆电子伏高能电子积分通量5分钟平均值
     * 3-  大于2兆电子伏高能电子通量随机误差和系统误差积分值
     *
     */
    private Integer ident;


    public void setTopFileName(String topFileName){
        this.topFileName=topFileName;
    }

    public String getTopFileName(){
        return topFileName;
    }

    public void setTypeFileName(String typeFileName){
        this.typeFileName=typeFileName;
    }

    public String getTypeFileName(){
        return typeFileName;
    }

    public void setGoesSatelliteName(String goesSatelliteName){
        this.goesSatelliteName=goesSatelliteName;
    }

    public String getGoesSatelliteName(){
        return goesSatelliteName;
    }
    public void setFiveMinFileName(String fiveMinFileName){
        this.fiveMinFileName=fiveMinFileName;
    }

    public String getFiveMinFileName(){
        return fiveMinFileName;
    }

    public void setYear(String year){
        this.year=year;
    }

    public String getYear(){
        return year;
    }

    public void setNcFileName(String ncFileName){
        this.ncFileName=ncFileName;
    }

    public String getNcFileName(){
        return ncFileName;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }

    public Date getCreateTime(){
        return createTime;
    }
    public void setIdent(Integer ident){
        this.ident=ident;
    }

    public Integer getIdent(){
        return ident;
    }

}
