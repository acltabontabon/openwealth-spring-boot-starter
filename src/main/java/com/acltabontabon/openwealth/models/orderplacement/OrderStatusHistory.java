package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {

    /**
     * Shows in which stage of its life cycle an order is.
     * FIXME: This should be an enum.
     */
    private String status;

    /**
     * Indicates the date and time at which the status has been updated into the status indicated
     * under "status" (UTC Timestamp). In accordance with ISO 8601.
     */
    private String statusDateTime;

}
