package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OptionStyle {
    AMERICAN("american"),
    EUROPEAN("european"),
    BERMUDAN("bermudan"),
    ASIAN("asian");

    private final String value;

    OptionStyle(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OptionStyle forValue(String value) {
        for (OptionStyle optionStyle : OptionStyle.values()) {
            if (optionStyle.value.equals(value)) {
                return optionStyle;
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
