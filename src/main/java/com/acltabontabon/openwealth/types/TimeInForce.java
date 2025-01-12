package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TimeInForce {

    DAY("day"),
    GOOD_TILL_CANCEL("goodTillCancel"),
    AT_THE_OPENING("atTheOpening"),
    IMMEDIATE_OR_CANCEL("immediateOrCancel"),
    FILL_OR_KILL("fillOrKill"),
    GOOD_TILL_CROSSING("goodTillCrossing"),
    GOOD_TILL_DATE("goodTillDate"),
    AT_THE_CLOSE("atTheClose"),
    GOOD_THROUGH_CROSSING("goodThroughCrossing"),
    AT_CROSSING("atCrossing"),
    GOOD_FOR_TIME("goodForTime"),
    GOOD_FOR_AUCTION("goodForAuction"),
    GOOD_FOR_MONTH("goodForMonth");

    private final String value;

    TimeInForce(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TimeInForce forValue(String value) {
        for (TimeInForce timeInForce : TimeInForce.values()) {
            if (timeInForce.value.equals(value)) {
                return timeInForce;
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
