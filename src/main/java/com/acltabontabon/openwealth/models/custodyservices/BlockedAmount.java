package com.acltabontabon.openwealth.models.custodyservices;

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
     * Specifies if a amount is positive or negative.
     * FIXME: This should be an enum
     */
    private String creditDebitIndicator;
}
