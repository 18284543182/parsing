package com.zklt.parsing.model.entity.nc.tab;


/**
 * 保留全限类名字段(暂且不用)
 */
public enum NcElectronEnum {

    //  电子伏微分电子通量平均值
    AVDIFFELECTROBFLUX("AvgDiffElectronFlux", "com.database.Service.vtx1896.kjtq.nc.tab.AvgDiffElectronFluxTab", 0),

    //  微分电子通量随机误差和系统误差积分值
    AVGDIFFELECTRONFLUXUNCERT("AvgDiffElectronFluxUncert", "com.database.Service.vtx1896.kjtq.nc.tab.AvgDiffElectronFluxUncertTab", 1),

    //  大于2兆电子伏高能电子积分通量5分钟平均值
    AVGINTELECTRONFLUX("AvgIntElectronFlux", "com.database.Service.vtx1896.kjtq.nc.tab.AvgIntElectronFluxTab", 2),

    //  大于2兆电子伏高能电子通量随机误差和系统误差积分值
    AVGINTELECTRONFLUXUNCERT("AvgIntElectronFluxUncert", "com.database.Service.vtx1896.kjtq.nc.tab.AvgIntElectronFluxUncertTab", 3);

    //  nc文件中属性名称
    private String attrrName;

    //  全限类名
    private String qxClassName;

    //  类型
    private Integer ident;

    // 构造方法
    private NcElectronEnum(String attrrName, String qxClassName, Integer ident) {
        this.attrrName = attrrName;
        this.qxClassName = qxClassName;
        this.ident = ident;
    }

    public static Integer getName(String attrrName) {
        for (NcElectronEnum val : NcElectronEnum.values()) {
            if (val.getAttrrName().equals(attrrName)) {
                return val.ident;
            }
        }
        return null;
    }

    // get set 方法
    public String getAttrrName() {
        return attrrName;
    }

    public void setName(String attrrName) {
        this.attrrName = attrrName;
    }

    public String getQxClassName() {
        return qxClassName;
    }

    public void setIndex(String qxClassName) {
        this.qxClassName = qxClassName;
    }
}
