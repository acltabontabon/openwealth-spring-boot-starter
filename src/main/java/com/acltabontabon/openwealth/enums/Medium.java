package com.acltabontabon.openwealth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

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
    public String toString() {
        return this.value;
    }
}
