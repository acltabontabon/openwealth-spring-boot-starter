package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.AccessType;
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
     */
    private AccessType accessType;

}
