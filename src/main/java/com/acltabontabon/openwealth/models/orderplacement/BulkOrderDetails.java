package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.models.custodyservices.PlaceOfTrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkOrderDetails {

    /**
     * Side taken by a party on an order.
     * FIXME: This should be an enum.
     */
    private String side;

    private OrderQuantity orderQuantity;

    /**
     * Absolute amount.
     */
    private String displayQuantity;

    /**
     * Indicates number of orders to be combined for average pricing and allocation.
     */
    private Integer numberOfAllocations;

    private FinancialInstrumentDetails financialInstrumentDetails;

    /**
     * Market in which a trade transaction is to be or has been executed.
     */
    private PlaceOfTrade placeOfTrade;

    /**
     * ISO 4217 code of the currency used for the amount.
     */
    private String currency;

    /**
     * Type of the execution of the order.
     * FIXME: This should be an enum.
     */
    private String executionType;

    /**
     * Limit price per unit of quantity (e.g. per share).
     */
    private String limitPrice;

    /**
     * Stop price per unit of quantity (e.g. per share).
     */
    private String stopPrice;

    /**
     * Specifies how long the order remains in effect.
     * FIXME: This should be an enum.
     */
    private String timeInForce;

    /**
     * Date and time of the order expiration (UTC Timestamp). In accordance with ISO 8601.
     */
    private String expiryDateTime;

}
