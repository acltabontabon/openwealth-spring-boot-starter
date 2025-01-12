package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentFrequency {

    ANNUAL("annual"),
    MONTHLY("monthly"),
    QUARTERLY("quarterly"),
    SEMI_ANNUAL("semiAnnual"),
    WEEKLY("weekly"),
    AT_MATURITY("atMaturity"),
    OTHER("other");

    private final String value;

    PaymentFrequency(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PaymentFrequency forValue(String value) {
        for (PaymentFrequency paymentFrequency : PaymentFrequency.values()) {
            if (paymentFrequency.value.equals(value)) {
                return paymentFrequency;
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
