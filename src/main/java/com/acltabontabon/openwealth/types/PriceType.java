package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceType {

    DEAL_PRICE("dealPrice"),
    MARKET_PRICE("marketPrice"),
    COST_PRICE("costPrice");

    private final String value;

    PriceType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PriceType forValue(String value) {
        for (PriceType price : PriceType.values()) {
            if (price.value.equals(value)) {
                return price;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
