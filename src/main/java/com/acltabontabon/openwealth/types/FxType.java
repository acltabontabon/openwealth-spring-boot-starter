package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FxType {

    CALCULATORY("calculatory"),
    DEAL_PRICE("dealPrice"),
    COST_PRICE("costPrice");

    private final String value;

    FxType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FxType forValue(String value) {
        for (FxType type : FxType.values()) {
            if (type.value.equals(value)) {
                return type;
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
