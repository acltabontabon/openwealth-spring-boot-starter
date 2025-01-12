package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountIdentificationType {

    IBAN("iban"),
    OTHER("other");

    private final String value;

    AccountIdentificationType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AccountIdentificationType forValue(String value) {
        for (AccountIdentificationType type : AccountIdentificationType.values()) {
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
