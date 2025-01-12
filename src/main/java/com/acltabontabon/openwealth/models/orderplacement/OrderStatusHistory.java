package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.OrderStatus;
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
     */
    private OrderStatus status;

    /**
     * Indicates the date and time at which the status has been updated into the status indicated
     * under "status" (UTC Timestamp). In accordance with ISO 8601.
     */
    private String statusDateTime;

}
