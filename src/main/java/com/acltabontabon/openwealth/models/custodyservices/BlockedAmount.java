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
public class BlockedAmount {

    /**
     * Absolute amount with the same amountType as the position itself.
     */
    private String amount;

    /**
     * Specifies if an amount is positive or negative.
     */
    private CreditDebitType creditDebitIndicator;
}
