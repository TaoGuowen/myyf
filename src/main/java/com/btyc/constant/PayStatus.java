package com.btyc.constant;

/**
 * 支付状态
 */
public enum PayStatus {

    /**
     * 初始化
     */
    INIT("01"),

    /**
     * 支付成功
     */
    SUCCESS("02"),

    /**
     * 退款
     */
    REFUND("03"),

    /**
     * 支付失败
     */
    FAIL("09");

    private String value;

    PayStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
