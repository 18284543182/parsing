package com.zklt.parsing.model.enums;

public enum MonthEnums {


    JANUARY("01", "01"),
    FEBURARY("02", "02"),
    MARCH("03", "03"),
    APRIL("04", "04"),
    MAY("05", "05"),
    JUNE("06", "06"),
    JULY("07", "07"),
    AUGUST("08", "08"),
    SEPTEMBER("09", "09"),
    OCTOBER("10", "10"),
    NOVEMBER("11", "11"),
    DECEMBER("12", "12");


    MonthEnums(String value, String text) {
        this.value = value;
        this.text = text;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    private String value;
    private String text;
}
