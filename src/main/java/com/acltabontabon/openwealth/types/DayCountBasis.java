package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayCountBasis {

    ACT_360("act_360"),
    ACT_365("act_365"),
    ACT_ACT_ICMA("act_actIcma"),
    ACT_ACT_ISDA("act_actIsda"),
    ACT_ACT_AFB("act_actAfb"),
    ACT_365L("act_365L"),
    BUS_252("bus_252"),
    U30_360("u30_360"),
    U30E_360_ICMA("u30E_360Icma"),
    U30E_360_ISDA("u30E_360Isda"),
    U30E_360("u30E_360"),
    U30U_360("u30U_360");

    private final String value;

    DayCountBasis(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DayCountBasis forValue(String value) {
        for (DayCountBasis dayCountBasis : DayCountBasis.values()) {
            if (dayCountBasis.value.equals(value)) {
                return dayCountBasis;
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
