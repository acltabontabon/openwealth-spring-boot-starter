package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionPriceType {

    DEAL_PRICE("dealPrice"),
    COST_PRICE("costPrice");

    private String value;

    TransactionPriceType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TransactionPriceType forValue(String value) {
        for (TransactionPriceType transactionPrice : TransactionPriceType.values()) {
            if (transactionPrice.value.equals(value)) {
                return transactionPrice;
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
