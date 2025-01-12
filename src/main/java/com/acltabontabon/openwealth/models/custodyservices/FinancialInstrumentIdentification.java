package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.SecurityIdentifierType;
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
     */
    private SecurityIdentifierType type;

}
