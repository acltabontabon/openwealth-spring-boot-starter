package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

@Data
public class SourceOfWealth {

    /**
     *
     */
    private Source source;

    /**
     * Amount.
     */
    private Long amount;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * 2-Letter ISO 3166-2 Country Code to indicate the country of origin of the assets.
     */
    private List<String> originsOfAssetsList;

}
