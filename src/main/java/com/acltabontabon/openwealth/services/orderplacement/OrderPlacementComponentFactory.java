package com.acltabontabon.openwealth.services.orderplacement;

import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.services.orderplacement.access.AccountAccessReader;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderCreator;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderDeleter;
import com.acltabontabon.openwealth.services.orderplacement.status.OrderReader;
import com.acltabontabon.openwealth.services.orderplacement.status.SingleOrderReader;

public interface OrderPlacementComponentFactory {

    OrderReader createOrderReader();
    AccountAccessReader createAccountAccessReader();

    // Order-related components
    SingleOrderReader createSingleOrderReader(String correlationId, String clientOrderId);
    OrderCreator createOrderCreator(String correlationId, RequestedOrder order);
    OrderDeleter createOrderDeleter(String correlationId, String clientOrderId);
}