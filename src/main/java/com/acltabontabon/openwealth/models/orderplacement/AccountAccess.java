package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAccess {

    private Account account;

    /**
     * Indicates what type of access the requestor has for the specified account.
     * FIXME: This should be an enum.
     */
    private String accessType;

}
