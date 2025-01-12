package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AllocationCancellationReasonCode {

    ACCOUNT_IDENTIFICATION_MISSING("accountIdentificationMissing"),
    ACCOUNT_INVALID_FOR_ORDER_PLACEMENT("accountInvalidForOrderPlacement"),
    ACCOUNT_TYPE_MISSING("accountTypeMissing"),
    ACCOUNT_TYPE_NOT_MATCHING_ACCOUNT("accountTypeNotMatchingAccount"),
    ALLOCATION_QUANTITY_TOO_HIGH("allocationQuantityTooHigh"),
    CASH_ACCOUNT_MISSING("cashAccountMissing"),
    CASH_ACCOUNT_NOT_FOUND("cashAccountNotFound"),
    INSUFFICIENT_BUYING_POWER("insufficientBuyingPower"),
    INSUFFICIENT_QUANTITY_AVAILABLE("insufficientQuantityAvailable"),
    INVALID_CURRENCY_FOR_ACCOUNT("invalidCurrencyForAccount"),
    PRETRADE_CHECK_FAILED("pretradeCheckFailed"),
    SAFEKEEPING_ACCOUNT_MISSING("safekeepingAccountMissing"),
    SAFEKEEPING_ACCOUNT_NOT_FOUND("safekeepingAccountNotFound"),
    TOO_MANY_CASH_ACCOUNTS_INDICATED("tooManyCashAccountsIndicated"),
    TOO_MANY_SAFEKEEPING_ACCOUNTS("tooManySafekeepingAccounts"),
    VALUE_TOO_HIGH("valueTooHigh"),
    ZERO_QUANTITY("zeroQuantity"),
    OTHER("other");

    private final String value;

    AllocationCancellationReasonCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AllocationCancellationReasonCode forValue(String value) {
        for (AllocationCancellationReasonCode allocationCancellationReasonCode : AllocationCancellationReasonCode.values()) {
            if (allocationCancellationReasonCode.value.equals(value)) {
                return allocationCancellationReasonCode;
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
