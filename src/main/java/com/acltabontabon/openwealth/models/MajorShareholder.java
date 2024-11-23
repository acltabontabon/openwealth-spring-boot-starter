package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class MajorShareholder {

    /**
     * Is this person a major shareholder?
     */
    private Boolean majorShareholderStatus;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String companyName;

    /**
     * Percentage of shareholding in the company.
     */
    private String shareOfOwnership;

    /**
     * ISIN of company.
     */
    private String isin;

}
