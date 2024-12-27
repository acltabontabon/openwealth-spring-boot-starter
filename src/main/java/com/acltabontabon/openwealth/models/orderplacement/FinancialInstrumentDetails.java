package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.models.custodyservices.FinancialInstrumentIdentification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInstrumentDetails {

    private FinancialInstrumentIdentification financialInstrumentIdentification;

}
