package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * Unambiguous identification for the account.
     */
    private String identification;

    /**
     * Indicates the type of the account.
     */
    private AccountType type;

}
