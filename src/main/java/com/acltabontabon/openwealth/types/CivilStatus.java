package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CivilStatus {

    DIVORCED("divorced"),
    LEGALLY_DIVORCED("legallyDivorced"),
    MARRIED("married"),
    SEPARATED("separated"),
    SINGLE("single"),
    STABLE_UNION("stableUnion"),
    WIDOW("widow");

    private final String value;

    CivilStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CivilStatus forValue(String value) {
        for (CivilStatus civilStatus : CivilStatus.values()) {
            if (civilStatus.value.equalsIgnoreCase(value)) {
                return civilStatus;
            }
        }
        throw new IllegalArgumentException("Invalid civil status: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
