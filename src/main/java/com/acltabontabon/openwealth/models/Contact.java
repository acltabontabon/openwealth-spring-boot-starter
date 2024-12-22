package com.acltabontabon.openwealth.models;

import com.acltabontabon.openwealth.enums.Medium;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    /**
     * Identification of contact detail provided by financial institution.
     */
    private String contactId;

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * Medium for contacting the customer.
     */
    private Medium medium;

    /**
     * Specifies the type of contact details.
     */
    private String mediumType;

    /**
     * Content of the contact detail, e.g. phone number, email-address, URL.
     */
    private String content;

    /**
     * Field to indicate the priority of the contact detail; e.g. preferred/main contact detail.
     */
    private String priority;

    /**
     * Used to indicate additional information regarding the contact detail.
     */
    private String additionalInformation;
}
