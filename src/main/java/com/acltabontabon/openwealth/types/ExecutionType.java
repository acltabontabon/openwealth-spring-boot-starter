package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExecutionType {

    MARKET("market"),
    LIMIT("limit"),
    STOP("stop"),
    STOP_LIMIT("stopLimit");

    private final String value;

    ExecutionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ExecutionType forValue(String value) {
        for (ExecutionType executionType : ExecutionType.values()) {
            if (executionType.value.equals(value)) {
                return executionType;
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
