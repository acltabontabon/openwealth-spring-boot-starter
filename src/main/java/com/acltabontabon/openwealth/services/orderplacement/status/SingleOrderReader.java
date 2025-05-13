package com.acltabontabon.openwealth.services.orderplacement.status;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleOrderReader extends ReadCommand<Result<Order>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.OrderPlacement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String clientOrderId;

    public OrderDeleter cancel() {
        return new OrderDeleter(restClient, apiProperties, asyncExecutor, correlationId, clientOrderId);
    }

    @Override
    protected Result<Order> execute() {
        try {
            Order order = restClient.get()
                .uri(builder -> builder.path(apiProperties.getOrder()).build(clientOrderId))
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                })
                .retrieve()
                .body(Order.class);

            return Result.success(order);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch order details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}

