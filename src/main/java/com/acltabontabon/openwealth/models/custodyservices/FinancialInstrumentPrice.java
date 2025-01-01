package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.CreditDebitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Provides information on different prices that are related to the functionality of the financial
 * instrument.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInstrumentPrice {

    /**
     * Declares the amount of the indicated price.
     */
    private String amount;

    /**
     * Indicates whether the price amount is depicted as unit price or percentage.
     * FIXME: This should be an enum.
     */
    private String amountType;

    /**
     * Specifies if a amount is positive or negative.
     */
    private CreditDebitType creditDebitIndicator;

    /**
     * ISO 4217 code of the currency.
     */
    private String currency;

    /**
     * Indicates the type of the price.
     * FIXME: This should be an enum.
     */
    private String type;
}
