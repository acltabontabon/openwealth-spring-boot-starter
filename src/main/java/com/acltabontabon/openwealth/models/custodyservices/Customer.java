package com.acltabontabon.openwealth.models.custodyservices;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /**
     * Unique and unambiguous identification used by the bank for the customer.
     */
    private String customerIdentification;

    /**
     * ISO 4217 code of the currency used by the customer.
     */
    private String customerReferenceCurrency;

    @Singular("addAccountInformation")
    private List<AccountInformation> accountInformationList;
}
