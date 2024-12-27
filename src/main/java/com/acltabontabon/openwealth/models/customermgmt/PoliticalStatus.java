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
public class PoliticalStatus {

    /**
     * Is this person a PEP?
     */
    private Boolean pepStatus;

    /**
     * PEP Function of the person.
     */
    private String pepFunction;

    /**
     * Is this person associated to a PEP?
     */
    private Boolean pepAssociations;

    /**
     * Name and function of the PEP the person is associated to.
     */
    private String pepRelation;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

    /**
     * Is the person subject to sanctions / on any sanction list?
     */
    private Boolean sanctions;
}
