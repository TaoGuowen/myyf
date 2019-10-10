package com.btyc.constant;

public enum OrderStatus {

    /**
     * 待支付
     */
    UNPAY("01"),

    /**
     * 已支付
     */
    PAY("02"),

    /**
     * 已退款
     */
    REFUND("03"),

    /**
     * 已删除
     */
    DELETE("09");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
