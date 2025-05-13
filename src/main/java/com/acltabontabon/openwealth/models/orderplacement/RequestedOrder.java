package com.acltabontabon.openwealth.models.orderplacement;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestedOrder {

    /**
     * Unambiguous identifier for the bulk order, as assigned by the instructing party.
     */
    private String clientOrderIdentification;

    /**
     * Date and time of the order placement (UTC Timestamp). In accordance with ISO 8601.
     */
    private String orderDateTime;

    /**
     * Unambiguous identifier for the bulk order, as assigned by the confirming party.
     */
    private String oderIdentification;

    private BulkOrderDetails bulkOrderDetails;

    @Singular("addRequestedAllocation")
    private List<RequestedAllocation> requestedAllocationList;

}
