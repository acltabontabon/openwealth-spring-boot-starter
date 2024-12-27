package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountOrUnits {

    private String amount;

    //FIXME: This should be an enum
    private String type;

    //FIXME: This should be an enum
    private String creditDebitIndicator;
}
