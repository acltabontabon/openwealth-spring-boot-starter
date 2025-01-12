package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OptionType {
    CALL("call"),
    PUT("put");

    private final String value;

    OptionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OptionType forValue(String value) {
        for (OptionType optionType : OptionType.values()) {
            if (optionType.value.equals(value)) {
                return optionType;
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
