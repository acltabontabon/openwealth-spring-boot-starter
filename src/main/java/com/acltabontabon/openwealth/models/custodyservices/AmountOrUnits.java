package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.AmountType;
import com.acltabontabon.openwealth.types.CreditDebitType;
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

    private AmountType type;

    private CreditDebitType creditDebitIndicator;
}
