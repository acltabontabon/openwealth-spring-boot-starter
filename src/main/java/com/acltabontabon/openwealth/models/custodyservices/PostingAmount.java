package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.CreditDebitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingAmount {

    /**
     * Absolute amount of the transaction.
     */
    private String amount;

    /**
     * ISO 4217 code of the currency used in the transaction.
     */
    private String currency;

    /**
     * Specifies if an amount is positive or negative.
     */
    private CreditDebitType creditDebitIndicator;

    private AccountInformation accountInformation;
}
