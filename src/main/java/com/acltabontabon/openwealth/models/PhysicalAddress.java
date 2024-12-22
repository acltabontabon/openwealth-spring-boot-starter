package com.acltabontabon.openwealth.models;

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
public class PhysicalAddress {

    /**
     * Identification of a division of a large organsiation or building.
     */
    private String department;

    /**
     * Name of the street or thoroughfare.
     */
    private String streetName;

    /**
     * Number that identifies the position of the building on a street.
     */
    private String buildingNumber;

    /**
     * Name of the building or house.
     */
    private String buildingName;

    /**
     * Floor or story within a building.
     */
    private String floor;

    /**
     * Numbered box in a post office, assigned to a person or organisation, where letters are kept
     * until called for.
     */
    private String postBox;

    /**
     * Building room number.
     */
    private String room;

    /**
     * Identifier consisting of a group of letters and/or numbers that is added to a postal address
     * to assist the sorting of mail
     */
    private String postCode;

    /**
     * Name of the town or city.
     */
    private String townName;

    /**
     * Specific location name within the town.
     */
    private String townLocationName;

    /**
     * Identifies a subdivision within a country sub-division.
     */
    private String districtName;

    /**
     * Identifies a subdivision of a country such as state, region, county.
     */
    private String countrySubDivision;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String country;

}
