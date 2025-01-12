package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {

    SECURITIES_EXCHANGE("securitiesExchange"),
    CASH("cash"),
    FEES("fees"),
    CREDIT("credit"),
    FX("fx"),
    TAXES("taxes"),
    CORPORATE_ACTIONS("corporateActions"),
    FIDUCIARY("fiduciary"),
    MONEY_MARKET("moneyMarket"),
    SECURITIES_TRANSFER("securitiesTransfer"),
    PHYSICAL("physical"),
    OTC("otc"),
    OTHERS("others");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TransactionType forValue(String value) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.value.equals(value)) {
                return transactionType;
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
