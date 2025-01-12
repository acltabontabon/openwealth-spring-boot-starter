package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InterestType {

    FIXED("fixed"),
    VARIABLE("variable"),
    STAGGERED("staggered");

    private final String value;

    InterestType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static InterestType forValue(String value) {
        for (InterestType interestType : InterestType.values()) {
            if (interestType.value.equals(value)) {
                return interestType;
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
