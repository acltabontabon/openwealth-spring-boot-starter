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
public class Valuation {

    /**
     * Absolute amount of the valuation.
     */
    private String amount;

    /**
     * ISO 4217 code of the currency of the valuation.
     */
    private String currency;

    /**
     * Specifies if an amount is positive or negative.
     * FIXME: This should be an enum
     */
    private CreditDebitType creditDebitIndicator;

}
