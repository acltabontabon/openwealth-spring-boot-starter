package com.acltabontabon.openwealth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    /**
     * Unique address ID assigned by the custody bank.
     */
    private String addressId;

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * Name of the address object.
     */
    private String addressName;

    /**
     * Indicates if this address is the domicile address.
     */
    private Boolean isDomicile;

    /**
     * 2-letter ISO 639-1 Language Code.
     */
    private String language;

    /**
     * Indicates if this address is used for mailing purposes.
     */
    private Boolean isMailingAddress;

    private SalutationDetails salutationDetails;

    private PhysicalAddress physicalAddress;
}
