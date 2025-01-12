package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.models.custodyservices.ForeignExchangeRate;
import com.acltabontabon.openwealth.types.CreditDebitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccruedInterests {

    /**
     * Specifies the number of days used for calculating the accrued interest amount.
     */
    private Integer numberDaysAccrued;

    /**
     * Absolute amount of the accrued interest.
     */
    private String amount;

    /**
     * ISO 4217 code of the currency used for the amount.
     */
    private String currency;

    /**
     * Specifies if the amount is a debit (negative) or credit (positive) amount.
     */
    private CreditDebitType creditDebitIndicator;

    private ForeignExchangeRate foreignExchangeRate;
}
