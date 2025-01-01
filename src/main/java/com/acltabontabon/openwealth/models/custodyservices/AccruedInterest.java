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
public class AccruedInterest {

    /**
     * Absolute interest amount that has accrued in between coupon payment periods.
     */
    private String amount;

    /**
     * ISO 4217 code of the currency of the interest amount.
     */
    private String currency;

    /**
     * Specifies if a amount is positive or negative.
     */
    private CreditDebitType creditDebitIndicator;

    /**
     * Number of days used for calculating the accrued interest amount.
     */
    private Integer numberOfDaysAccrued;
}
