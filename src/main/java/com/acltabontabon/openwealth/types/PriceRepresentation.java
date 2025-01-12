package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceRepresentation {

    PER_UNIT("perUnit"),
    PERCENTAGE("percentage");

    private final String value;

    PriceRepresentation(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PriceRepresentation forValue(String value) {
        for (PriceRepresentation priceAmount : PriceRepresentation.values()) {
            if (priceAmount.value.equals(value)) {
                return priceAmount;
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
