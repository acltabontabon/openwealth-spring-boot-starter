package com.acltabontabon.openwealth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_EMPTY)
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
    @Singular("addAssetAllocation")
    private List<AssetAllocation> assetAllocationList;
}
