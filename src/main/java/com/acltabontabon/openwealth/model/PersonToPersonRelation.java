package com.acltabontabon.openwealth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class PersonToPersonRelation {

    /**
     * Type of relation between two persons.
     * FIXME: This should be an ENUM.
     */
    private String relation;

    /**
     * Field indicates the person to person relation type. It shall only be used if the relation
     * type is not part of the ENUM of the attribute "relation" hence in exceptional cases (in case
     * of relation type "other").
     */
    private String relationOverride;

    private Person relatedPerson;

    /**
     * External customer/person ID.
     */
    private String externalReference;

}
