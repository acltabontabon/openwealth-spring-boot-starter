package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class CorporateInsider {

    /**
     * Is this person an associate to a Corporate Insider?
     */
    private Boolean corporateInsiderAssociation;

    /**
     * Nature/Type of relationship with Corporate Insider.
     */
    private String relation;

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
