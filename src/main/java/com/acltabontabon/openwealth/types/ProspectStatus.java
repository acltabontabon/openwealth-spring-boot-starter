package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProspectStatus {

    OPEN("open"),
    DENIED("denied"),
    ACCEPTED("accepted"),
    PLEASE_CONTACT_BANK_ADVISOR("pleaseContactBankAdvisor");

    private final String value;

    ProspectStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProspectStatus forValue(String value) {
        for (ProspectStatus prospectStatus : ProspectStatus.values()) {
            if (prospectStatus.value.equalsIgnoreCase(value)) {
                return prospectStatus;
            }
        }
        throw new IllegalArgumentException("Invalid prospect status: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
