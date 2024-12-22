package com.acltabontabon.openwealth.model;

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
