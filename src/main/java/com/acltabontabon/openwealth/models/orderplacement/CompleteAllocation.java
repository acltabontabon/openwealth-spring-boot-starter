package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteAllocation {

    /**
     * Unambiguous identifier of the individual allocation of the bulk order, as assigned by the
     * confirming party. Corresponds with the transactionIdentification of the OpenWealth Custody
     * Services API.
     */
    private String allocationIdentification;

    /**
     * Total quantity (e.g. number of shares) filled.
     */
    private String executedQuantity;

    /**
     * The average price for quantity that has traded today.
     */
    private String averagePrice;

    /**
     * Indicates the booking date for an order or allocation.
     */
    private BookingDate bookingDate;

    private BillingDetails billingDetails;

}
