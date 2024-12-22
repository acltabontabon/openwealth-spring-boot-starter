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
public class SalutationDetails {

    /**
     * Reception restrictions for the physical delivery of postal items.
     */
    private String receptionRestriction;

    /**
     * Additional information that follows a person's name, e.g. qualifications/titles such as
     * Doctor of Philosophy (PhD).
     */
    private String title;

    /**
     * Salutation of the person.
     */
    private String salutation;

    /**
     * Field to indicate the salutation. It shall only be used if the salutation type is not part
     * of the ENUM of the attribute "salutation" hence in exceptional cases (in case of salutation
     * "other"). If the salutation is not listed in the ENUM of the attribute salutation then this
     * field can be used.
     */
    private String salutationOverride;

    /**
     * Name by which the organisation is known and which is usually used to identify that
     * organisation.
     */
    private String organisationName;

    /**
     * First name of the person.
     */
    private String givenName;

    /**
     * Last name of the person.
     */
    private String lastName;

    /**
     * When the individual resides at another person's address, the name of the other person.
     */
    private String careOf;

    /**
     * When the mail should be distributed to a specific person within a company.
     */
    private String toTheAttentionOf;
}
