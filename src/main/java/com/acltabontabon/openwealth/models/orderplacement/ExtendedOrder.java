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
public class ExtendedOrder {

    /**
     * Unambiguous identifier for the bulk order, as assigned by the instructing party.
     */
    private String clientOrderIdentification;

    /**
     * Date and time the order has been received. In accordance with ISO 8601.
     */
    private String orderDateTime;

    /**
     * Unambiguous identifier for the bulk order, as assigned by the confirming party.
     */
    private String orderIdentification;

    private BulkOrderDetails bulkOrderDetails;

    /**
     * Extended details regarding the allocations including validity of the allocation and billing
     * details.
     */
    @Singular("addAllocation")
    private List<Allocation> allocationList;
}
