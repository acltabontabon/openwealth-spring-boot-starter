package com.acltabontabon.openwealth.services.orderplacement.status;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderCreator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class OrderReader extends ReadCommand<Result<List<Order>>> {

    private final RestClient restClient;
    private final ApiProperties.OrderPlacement apiProperties;

    private String correlationId;

    public OrderReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleOrderReader withClientOrderId(String clientOrderId) {
        return new SingleOrderReader(restClient, apiProperties, correlationId, clientOrderId);
    }

    public OrderCreator createNew(RequestedOrder order) {
        return new OrderCreator(restClient, apiProperties, this.correlationId, order);
    }

    @Override
    protected Result<List<Order>> execute() {
        try {
            List<Order> response = restClient.get()
                .uri(apiProperties.getOrders())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of orders", e.getStatusMessage());
        }
    }
}
