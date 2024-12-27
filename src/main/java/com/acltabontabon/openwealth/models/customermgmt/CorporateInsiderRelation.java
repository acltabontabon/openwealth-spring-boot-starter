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
public class CorporateInsiderRelation {

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
