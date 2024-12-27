package com.acltabontabon.openwealth.models.custodyservices;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private String identification;

    private String externalReference;

    private String currency;

    private AmountOrUnits amountOrUnits;

    private String positionName;

    @Singular("addPrice")
    private List<Price> priceList;

    private String safekeepingPlace;

    private String additionalCustodianInformation;

    private AccruedInterest accruedInterest;

    private BlockedAmount blockedAmount;

    private String positionAdditionalDetails;

    private FinancialInstrument financialInstrument;
}
