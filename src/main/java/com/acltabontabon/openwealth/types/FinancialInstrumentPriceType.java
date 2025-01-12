package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FinancialInstrumentPriceType {

    STRIKE("strike"),
    CAP("cap"),
    FLOOR("floor"),
    CONVERSION("conversion"),
    EXERCISE("exercise"),
    SUBSCRIPTION("subscription"),
    AUTOCALL("autocall"),
    BARRIER_LEVEL("barrierLevel"),
    BONUS_LEVEL("bonusLevel"),
    FINANCING_LEVEL("financingLevel"),
    INITIAL_FIXING("initialFixing"),
    KNOCK_OUT("knockOut"),
    STOP_LOSS("stopLoss"),
    KNOCK_IN("knockIn");

    private final String value;

    FinancialInstrumentPriceType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FinancialInstrumentPriceType forValue(String value) {
        for (FinancialInstrumentPriceType financialInstrumentPriceType : FinancialInstrumentPriceType.values()) {
            if (financialInstrumentPriceType.value.equals(value)) {
                return financialInstrumentPriceType;
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
