package com.btyc.constant;

/**
 * 订单类型
 */
public enum OrderType {
    /**
     * 卡片类
     */
    PRODUCT("01"),
    /**
     *产品类
     */
    CARD("02");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
