package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class AssetLocation {

    private String type;
    private Long amount;
    private String currency;
    private String percentageOfAllocation;

}
