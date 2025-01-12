package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {

    CASH_ACCOUNT("cashAccount"),
    SAFEKEEPING_ACCOUNT("safekeepingAccount"),
    OTHER("other");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AccountType forValue(String value) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.value.equals(value)) {
                return accountType;
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
