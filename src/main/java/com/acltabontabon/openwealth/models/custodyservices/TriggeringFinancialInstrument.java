package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggeringFinancialInstrument {

    /**
     * Identification of the instrument.
     */
    private String identification;

    /**
     * Type of the instrument ID. isin is preferred.
     * FIXME: This should be an enum
     */
    private String type;

    /**
     * Name of the financial instrument in free format text.
     */
    private String name;

    /**
     * ISO 4217 code of the currency.
     */
    private String currency;

    private AmountOrUnits triggeringAmountOrUnits;
}
