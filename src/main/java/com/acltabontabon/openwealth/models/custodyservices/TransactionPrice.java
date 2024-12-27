package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPrice {

    /**
     * Price indication.
     */
    private String amount;

    /**
     * Specifies the type of price.
     * FIXME: This should be an enum
     */
    private String amountType;

    /**
     * Describes the type of price stated.
     * FIXME: This should be an enum
     */
    private String transactionPriceType;

    /**
     * ISO 4217 code of the currency
     */
    private String currency;
}
