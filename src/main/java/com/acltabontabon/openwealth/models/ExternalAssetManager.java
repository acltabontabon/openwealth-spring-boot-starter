package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class ExternalAssetManager {

    /**
     * Is the customer an EAM customer?
     */
    private Boolean eamCustomer;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String eamName;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String eamDomicile;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String eamEmployee;

    /**
     * Has the EAM met the customer personally?
     */
    private Boolean eamRelation;

    /**
     * How was the relationship between EAM and customer formed?
     */
    private String eamRelationInitiation;

    /**
     * Period of time e.g. a job was held / a company owned.
     */
    private String eamRelationPeriod;

}
