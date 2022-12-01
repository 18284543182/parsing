package com.zklt.parsing.model.enums;

public enum DateEnum {

    Jan(1, "Jan"), Feb(2,"Feb"), Mar(3,"Mar"),Apr(4,"Apr"),
    May(5,"May"),Jun(6,"Jun"),Jul(7,"Jul"),Aug(8,"Aug"),
    Sept(3,"Sept"),Oct(10,"Oct"),Nov(11,"Nov"),Dec(12,"Dev");


    // 成员变量
    private int num;
    private String sx;
    // 构造方法
    private DateEnum(int num, String sx) {
        this.num = num;
        this.sx = sx;
    }
    // 普通方法
    public static Integer getName(String sx) {
        for (DateEnum c : DateEnum.values()) {
            if (c.getSx().equals(sx)) {
                return c.num;
            }
        }
        return null;
    }

    public String getSx(){
        return sx;
    }

    public void setSx(String sx){
        this.sx=sx;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num=num;
    }
}
