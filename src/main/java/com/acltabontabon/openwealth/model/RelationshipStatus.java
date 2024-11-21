package com.acltabontabon.openwealth.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationshipStatus {

    ACTIVE("active"),
    INACTIVE("inactive");

    private final String value;

    RelationshipStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static RelationshipStatus forValue(String value) {
        return RelationshipStatus.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
