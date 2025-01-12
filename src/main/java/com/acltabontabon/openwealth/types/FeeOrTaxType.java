package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FeeOrTaxType {

    STAMP_DUTY("stampDuty"),
    WITHHOLDING_TAX("withholdingTax"),
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

    FeeOrTaxType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FeeOrTaxType forValue(String value) {
        for (FeeOrTaxType feeOrTaxType : FeeOrTaxType.values()) {
            if (feeOrTaxType.value.equals(value)) {
                return feeOrTaxType;
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
