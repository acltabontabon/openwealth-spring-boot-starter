package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class MajorShareholderRelation {

    /**
     * Is this person an associate to a Major Shareholder?
     */
    private Boolean majorShareholderAssociation;

    /**
     * Nature/Type of relationship with major shareholder and name of major shareholder.
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
