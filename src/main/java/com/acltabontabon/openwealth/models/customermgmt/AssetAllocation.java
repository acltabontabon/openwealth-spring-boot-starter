package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class AssetAllocation {

    /**
     * Indicates the type of the asset.
     */
    private String type;

    /**
     * Amount.
     */
    private Long amount;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * % of total asset allocation.
     */
    private String percentageOfAllocation;

    /**
     * List of attributes to provide detailed information about the asset e.g. the domicile of a
     * specific property when asset type is realEstate or the name of the company if the asset type
     * is companyShareholdings.
     */
    private FurtherDetailsAsset furtherDetailsAsset;

}
