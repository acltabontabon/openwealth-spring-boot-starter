package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditDebitType {

    CREDIT("credit"),
    DEBIT("debit");

    private final String value;

    CreditDebitType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CreditDebitType forValue(String value) {
        for (CreditDebitType creditDebit : CreditDebitType.values()) {
            if (creditDebit.value.equalsIgnoreCase(value)) {
                return creditDebit;
            }
        }
        throw new IllegalArgumentException("Invalid credit/debit type: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
