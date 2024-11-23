package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

@Data
public class TotalWealth {

    /**
     * Amount.
     */
    private Long amountTotalNetAssets;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * Reference year.
     */
    private String referenceYear;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

    /**
     * Asset Allocation of customer.
     */
    private List<AssetAllocation> assetAllocationList;
}
