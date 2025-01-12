package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CurrentStatus {

    OPEN("open"),
    IN_PROGRESS("inProgress"),
    REJECTED("rejected"),
    DONE("done");

    private final String value;

    CurrentStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CurrentStatus forValue(String value) {
        for (CurrentStatus currentStatus : CurrentStatus.values()) {
            if (currentStatus.value.equals(value)) {
                return currentStatus;
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
