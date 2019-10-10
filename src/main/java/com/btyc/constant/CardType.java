package com.btyc.constant;

/**
 * 卡类型
 * （01:次卡；02：月卡；03：活动卡）
 */
public enum CardType {
    /**
     * 次卡
     */
    TIME_CARD("01"),

    /**
     * 月卡
     */
    MONTH_CARD("02");

    private String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
