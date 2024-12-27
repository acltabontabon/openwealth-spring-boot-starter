package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemainingAllocation {

    /**
     * Total quantity (e.g. number of shares) filled.
     */
    private String executedQuantity;

    /**
     * Quantity open for further execution. If the status is cancelled, expired, or rejected (in
     * which case the order is no longer active) then remainingQuantity could be 0, otherwise
     * remainingQuantity = orderQuantity - executedQuantity.
     */
    private String remainingQuantity;

}
