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
public class Allocation {

    private RequestedAllocation requestedAllocation;

    /**
     * Reasons for cancellation of the allocation.
     */
    @Singular("addAllocationCancellationReason")
    private List<AllocationCancellationReason> allocationCancellationReasonList;

    /**
     * Provides an overview of what quantity has already been allocated to the allocation and what
     * quantity is still outstanding.
     */
    private RemainingAllocation remainingAllocation;;

    /**
     * List of completed allocations.
     */
    private List<CompleteAllocation> completeAllocationList;

}
