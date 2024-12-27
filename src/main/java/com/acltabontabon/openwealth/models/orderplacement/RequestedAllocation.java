package com.acltabontabon.openwealth.models.orderplacement;

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
public class RequestedAllocation {

    /**
     * List of affected cashAccount and safekeepingAccount.
     */
    @Singular("addAccount")
    private List<Account> accounts;

    /**
     * Unambiguous identifier for the individual allocation of the bulk order, as assigned by the
     * instructing party.
     */
    private String clientAllocationIdentification;

    /**
     * Quantity to be allocated to specific safekeeping account
     */
    private String amount;
}
