package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    ACKNOWLEDGED("acknowledged"),
    ACCEPTED("accepted"),
    CUSTOMER_RELEASE("customerRelease"),
    PLACED("placed"),
    PARTIALLY_FILLED("partiallyFilled"),
    FILLED("filled"),
    EXECUTED("executed"),
    CANCELLED("cancelled"),
    PARTIALLY_CANCELLED("partiallyCancelled"),
    MARKET_CANCELLED("marketCancelled"),
    PENDING_CANCEL("pendingCancel"),
    REJECTED("rejected"),
    PARTIALLY_REJECTED("partiallyRejected"),
    MARKET_REJECTED("marketRejected"),
    EXPIRED("expired"),
    PARTIALLY_EXPIRED("partiallyExpired"),
    MARKET_EXPIRED("marketExpired");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static OrderStatus forValue(String value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.status.equals(value)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.status;
    }

}
