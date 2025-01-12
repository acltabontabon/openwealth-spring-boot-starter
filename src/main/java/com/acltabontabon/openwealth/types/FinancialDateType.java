package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FinancialDateType {

    ISSUE_DATE("issueDate"),
    MATURITY_DATE("maturityDate"),
    NEXT_CALLABLE_DATE("nextCallableDate"),
    EXERCISE_DATE("exerciseDate"),
    EXPIRY_DATE("expiryDate"),
    COUPON_DATE("couponDate"),
    FLOATING_RATE_FIXING_DATE("floatingRateFixingDate"),
    CONVERSION_DATE("conversionDate"),
    PUTABLE_DATE("putableDate"),
    DATED_DATE("datedDate"),
    FIRST_PAYMENT_DATE("firstPaymentDate"),
    NEXT_FACTOR_DATE("nextFactorDate");

    private final String value;

    FinancialDateType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FinancialDateType forValue(String value) {
        for (FinancialDateType financialDateType : FinancialDateType.values()) {
            if (financialDateType.value.equals(value)) {
                return financialDateType;
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
