package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInstrumentIdentification {

    /**
     * Instrument identification.
     */
    private String identification;

    /**
     * Type of the instrument ID. isin is preferred.
     * FIXME: This should be an enum
     */
    private String type;

}
