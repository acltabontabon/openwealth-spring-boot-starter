package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.OrderStatus;
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
public class OrderState {

    /**
     * Shows in which stage of its life cycle an order is.
     */
    private OrderStatus status;

    /**
     * Indicates the date and time at which the status has been updated into the status indicated
     * under "status" (UTC Timestamp). In accordance with ISO 8601.
     */
    private String statusDateTime;

    /**
     * Indicates the reasons, why an order is invalid/cancelled/rejected
     */
    @Singular("addOrderCancellationReason")
    private List<OrderCancellationReason> orderCancellationReasonList;

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

    /**
     * Calculated average price of all fills on this order.
     */
    private String averagePrice;

    /**
     * For GoodTill(GT) orders the orderQuantity less all quantity (adjusted for stock splits) that
     * traded on previous days. DayOrderQuantity = OrderQuantity - (executedQuantity -
     * dayExecutedQuantity).
     */
    private String dayOrderQuantity;

    /**
     * For GT orders the quantity on a GT order that has traded today.
     */
    private String dayExecutedQuantity;

    /**
     * The average price for quantity that has traded today.
     */
    private String dayAveragePrice;

    /**
     * For Fixed Income: Amortization Factor for deriving Current face from Original face for ABS or
     * MBS securities, note the fraction may be greater than, equal to or less than . In TIPS
     * securities this is the Inflation index. Qty * Factor * Price = Gross Trade Amount
     *
     * For Derivatives: Contract Value Factor by which price must be adjusted to determine the true
     * nominal value of one future/options contract. (Qty * Price) * Factor = Nominal Value
     */
    private String factor;

    /**
     * Provides all the status that the order was in at a certain time in its lifecycle.
     */
    @Singular("addOrderStatusHistory")
    private List<OrderStatusHistory> orderStatusHistoryList;
}
