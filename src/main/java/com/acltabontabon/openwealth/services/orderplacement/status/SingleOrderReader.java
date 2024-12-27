package com.acltabontabon.openwealth.services.orderplacement.status;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleOrderReader extends ReadCommand<Result<Order>> {

    private final RestClient restClient;
    private final ApiProperties.OrderPlacement apiProperties;

    private final String correlationId;
    private final String clientOrderId;

    public OrderDeleter cancelOrder() {
        return new OrderDeleter(restClient, apiProperties, correlationId, clientOrderId);
    }

    @Override
    protected Result<Order> execute() {
        try {
            Order order = restClient.get()
                .uri(builder -> builder.path(apiProperties.getOrder()).build(this.clientOrderId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Order.class);

            return Result.success(order);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch order details", e.getStatusMessage());
        }
    }
}

