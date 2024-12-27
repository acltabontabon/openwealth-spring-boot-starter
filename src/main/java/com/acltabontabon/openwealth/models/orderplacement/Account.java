package com.acltabontabon.openwealth.models.orderplacement;

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
     * FIXME: This should be an enum.
     */
    private String type;

}
