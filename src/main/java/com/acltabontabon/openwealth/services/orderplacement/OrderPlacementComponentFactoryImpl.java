package com.acltabontabon.openwealth.services.orderplacement;

import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.orderplacement.access.AccountAccessReader;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderCreator;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderDeleter;
import com.acltabontabon.openwealth.services.orderplacement.status.OrderReader;
import com.acltabontabon.openwealth.services.orderplacement.status.SingleOrderReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class OrderPlacementComponentFactoryImpl implements OrderPlacementComponentFactory {

    private final RestClient restClient;
    private final OpenWealthApiProperties.OrderPlacement apiProperties;
    private final TaskExecutor asyncExecutor;

    @Override
    public OrderReader createOrderReader() {
        return new OrderReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public AccountAccessReader createAccountAccessReader() {
        return new AccountAccessReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public SingleOrderReader createSingleOrderReader(String correlationId, String clientOrderId) {
        return new SingleOrderReader(restClient, apiProperties, asyncExecutor, correlationId, clientOrderId);
    }

    @Override
    public OrderCreator createOrderCreator(String correlationId, RequestedOrder order) {
        return new OrderCreator(restClient, apiProperties, asyncExecutor, correlationId, order);
    }

    @Override
    public OrderDeleter createOrderDeleter(String correlationId, String clientOrderId) {
        return new OrderDeleter(restClient, apiProperties, asyncExecutor, correlationId, clientOrderId);
    }
}