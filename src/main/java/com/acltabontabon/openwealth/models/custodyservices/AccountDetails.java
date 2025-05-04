package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    /**
     * Unique and unambiguous identification for the account.
     */
    private String accountIdentification;

    /**
     * Indicates the type of the account identification.
     * TODO: Revisit this. This is initially set as AccountIdentificationType (patterned after
     *  the enum in the OpenWealth API) however, the sandbox response uses AccountType - so I'm
     *  not sure what is 'correct'.
     */
    private AccountType accountIdentificationType;
}
