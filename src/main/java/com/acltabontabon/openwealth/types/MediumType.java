package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MediumType {

    EMAIL("email"),
    PHONE("phone"),
    FAX("fax"),
    MOBILE("mobile"),
    WEBSITE("website"),
    SOCIAL_MEDIA("socialMedia"),
    OTHERS("others");

    private final String value;

    MediumType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MediumType forValue(String value) {
        for (MediumType medium : MediumType.values()) {
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
