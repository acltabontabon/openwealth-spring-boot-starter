package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderSide {

    BUY("BUY"),
    SELL("SELL");

    private final String value;

    OrderSide(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OrderSide forValue(String value) {
        for (OrderSide orderSide : OrderSide.values()) {
            if (orderSide.value.equals(value)) {
                return orderSide;
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
