package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class TotalWealth {

    private Long amountTotalNetAssets;
    private String currency;
    private String referenceYear;
    private String additionalInformation;

}
