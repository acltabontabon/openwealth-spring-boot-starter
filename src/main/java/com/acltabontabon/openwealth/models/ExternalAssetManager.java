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
public class ExternalAssetManager {

    /**
     * FIXME: DOCUMENT ME!
     */
    private String relationshipManager;

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
