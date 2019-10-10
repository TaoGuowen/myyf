package com.btyc.constant;

public enum YesNo {

    /**
     * 次卡
     */
    YES("1"),

    /**
     * 月卡
     */
    NO("0");

    private String value;

    YesNo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
