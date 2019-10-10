package com.btyc.constant;

/**
 * 支付方式
 */
public enum PayMethod {

    /**
     * 微信支付
     */
    WEIXIN("01"),

    /**
     * 支付宝支付
     */
    ZHIFUBAO("02"),

    /**
     * 卡支付
     */
    CARD("09");

    private String value;

    PayMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
