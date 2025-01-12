package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AmountType {

    UNITS_NUMBER("unitsNumber"),
    NOMINAL("nominal");

    private final String value;

    AmountType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AmountType forValue(String value) {
        for (AmountType amount : AmountType.values()) {
            if (amount.value.equals(value)) {
                return amount;
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
