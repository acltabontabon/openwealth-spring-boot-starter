package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SecurityIdentifierType {

    ISIN("isin"),
    SEDOL("sedol"),
    CUSIP("cusip"),
    RIC("ric"),
    TICKER_SYMBOL("tickerSymbol"),
    BLOOMBERG("bloomberg"),
    CTA("cta"),
    QUICK("quick"),
    WERTPAPIER("wertpapier"),
    DUTCH("dutch"),
    VALOREN("valoren"),
    SICOVAM("sicovam"),
    BELGIAN("belgian"),
    COMMON("common"),
    OTHER_PROPRIETARY_IDENTIFICATION("otherProprietaryIdentification");

    private final String value;

    SecurityIdentifierType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SecurityIdentifierType forValue(String value) {
        for (SecurityIdentifierType securityIdentifierType : SecurityIdentifierType.values()) {
            if (securityIdentifierType.value.equals(value)) {
                return securityIdentifierType;
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
