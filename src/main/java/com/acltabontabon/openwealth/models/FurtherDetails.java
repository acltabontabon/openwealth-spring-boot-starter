package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
public class FurtherDetails {

    /**
     * Information on family members and related persons.
     */
    private String familyMembers;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

}
