package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class CorporateInsiderPerson {

    /**
     * Is this person a corporate Insider?
     */
    private Boolean corporateInsiderStatus;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String companyName;

    /**
     * Function/position which the person helds at the company.
     */
    private String position;

    /**
     * ISIN of company.
     */
    private String isin;

}
