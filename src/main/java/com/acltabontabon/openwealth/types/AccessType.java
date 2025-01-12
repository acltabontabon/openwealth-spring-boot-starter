package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessType {

    READ("read"),
    WRITE("write");

    private final String value;

    AccessType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AccessType forValue(String value) {
        for (AccessType accessType : AccessType.values()) {
            if (accessType.value.equals(value)) {
                return accessType;
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
