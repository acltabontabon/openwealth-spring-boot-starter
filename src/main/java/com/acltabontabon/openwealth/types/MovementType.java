package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MovementType {

    SECURITY("security"),
    CASH("cash"),
    REVENUE("revenue"),
    STAMP_DUTY("stampDuty"),
    WITHHOLDING_TAX("withholdingTax"),
    ADDITIONAL_WITHHOLDING_TAX("additionalWithholdingTax"),
    COUPON("coupon"),
    PREMIUM("premium"),
    CAPITAL_GAIN_TAX("capitalGainTax"),
    FINANCIAL_TRANSACTION_TAX("financialTransactionTax"),
    OTHER_TAXES("otherTaxes"),
    TRANSACTION_FEE("transactionFee"),
    BROKERAGE_FEE("brokerageFee"),
    MANAGEMENT_FEE("managementFee"),
    COURTAGE("courtage"),
    CUSTODY_FEE("custodyFee"),
    EXCHANGE_FEE("exchangeFee"),
    THIRD_PARTY_FEE("thirdPartyFee"),
    OTHER_FEE("otherFee"),
    OTHERS("others");

    private final String value;

    MovementType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MovementType forValue(String value) {
        for (MovementType movementType : MovementType.values()) {
            if (movementType.value.equals(value)) {
                return movementType;
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
