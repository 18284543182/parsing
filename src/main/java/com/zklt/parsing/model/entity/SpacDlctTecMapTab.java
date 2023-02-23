package com.zklt.parsing.model.entity;


import lombok.Data;

/**
 * @author xiejian
 * @date 2023/02/03 09:44
 * @Description: SZT_ISM01_DTS_L11_30M_20170816000000.dat
 */
@Data
public class SpacDlctTecMapTab extends Message{

    public String yyyy;
    public String MM;
    public String dd;
    public String hh;
    public String mm;
    public String ss;
    public String PRN;
    public String Az;
    public String Elv;
    public String S4;
    public String Sig;
    public String TEC0;
}
