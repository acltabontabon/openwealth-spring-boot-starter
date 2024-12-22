package com.acltabontabon.openwealth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class FurtherDetailsAsset {

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc..
     */
    private String nameOfBank;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String domicileOfBank;

    /**
     * Indicates the type of property.
     */
    private String typeOfProperty;

    /**
     * Usage of the property.
     */
    private String usageOfProperty;

    /**
     * Place of the property.
     */
    private String placeOfProperty;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String countryOfProperty;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfCompany;

    /**
     * Percentage of shareholding in the company.
     */
    private Integer shareholdingsInPercent;

    /**
     * Type of non-tradable assets.
     */
    @JsonProperty("typeOfNon-tradableAsset")
    private String typeOfNonTradableAsset;

    /**
     * Free text field to provide additional information.
     */
    @JsonProperty("additonalInformation") // This is a typo in the OpenWealth API
    private String additionalInformation;
}
