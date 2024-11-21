package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class PersonToPersonRelation {

    /**
     * Type of relation between two persons.
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
