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
    private Integer shareOfOwnership;

    /**
     * ISIN of company.
     */
    private String isin;

}
