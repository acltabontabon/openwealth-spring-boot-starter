package com.acltabontabon.openwealth.types;

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
        for (RelationshipStatus relationshipStatus : RelationshipStatus.values()) {
            if (relationshipStatus.value.equals(value)) {
                return relationshipStatus;
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
