package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Medium {

    EMAIL("email"),
    PHONE("phone"),
    FAX("fax"),
    MOBILE("mobile"),
    WEBSITE("website"),
    SOCIAL_MEDIA("socialMedia"),
    OTHERS("others");

    private String value;

    Medium(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Medium forValue(String value) {
        for (Medium medium : Medium.values()) {
            if (medium.value.equals(value)) {
                return medium;
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
