package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DateType {
    TRANSACTION_DATE("transactionDate"),
    BOOKING_DATE("bookingDate"),
    VALUE_DATE("valueDate"),
    EFFECTIVE_SETTLEMENT_DATE("effectiveSettlementDate"),
    SETTLEMENT_DATE("settlementDate"),
    PERFORMANCE_DATE("performanceDate");

    private final String value;

    DateType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DateType forValue(String value) {
        for (DateType dateType : DateType.values()) {
            if (dateType.value.equalsIgnoreCase(value)) {
                return dateType;
            }
        }
        throw new IllegalArgumentException("Invalid date type: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
