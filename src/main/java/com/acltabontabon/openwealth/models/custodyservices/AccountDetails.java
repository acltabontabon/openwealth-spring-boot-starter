package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.AccountIdentificationType;
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
     */
    private AccountIdentificationType accountIdentificationType;
}
