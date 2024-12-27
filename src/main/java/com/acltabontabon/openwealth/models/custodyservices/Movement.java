package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    private AmountOrUnits amountOrUnits;

    /**
     * ISO 4217 code of the currency.
     */
    private String currency;

    /**
     * Describes which kind of movement is reported from a banks perspective.
     * FIXME: This should be an enum
     */
    private String movementType;

    /**
     * Provides further details on an informative level, which goes beyond the granularity of the
     * movementType.
     */
    private String movementTypeAdditionalInformation;

    private FinancialInstrument financialInstrument;

    /**
     * Identification for the position given by the bank.
     */
    private String positionIdentification;

    /**
     * Indicates the affected account of this movement.
     */
    private AccountDetails accountDetails;
}
