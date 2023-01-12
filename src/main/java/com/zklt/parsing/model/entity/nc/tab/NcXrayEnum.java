package com.zklt.parsing.model.entity.nc.tab;

public enum NcXrayEnum {


    //
    XRSA_FLUX("xrsa_flux", 0),


    XRSA_FLAG("xrsa_flag", 1),


    XRSA_FLAG_EXCLUDED("xrsa_flag_excluded", 2),

    XRSA2_FLUX("xrsa2_flux", 3);


    private String attrrName;

    //  类型
    private Integer ident;

    // 构造方法
    private NcXrayEnum(String attrrName, Integer ident) {
        this.attrrName = attrrName;
        this.ident = ident;
    }

    public static Integer getName(String attrrName) {
        for (NcXrayEnum val : NcXrayEnum.values()) {
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
}
