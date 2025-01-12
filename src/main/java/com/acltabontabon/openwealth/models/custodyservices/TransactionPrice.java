package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.PriceRepresentation;
import com.acltabontabon.openwealth.types.TransactionPriceType;
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
     */
    private PriceRepresentation amountType;

    /**
     * Describes the type of price stated.
     */
    private TransactionPriceType transactionPriceType;

    /**
     * ISO 4217 code of the currency
     */
    private String currency;
}
