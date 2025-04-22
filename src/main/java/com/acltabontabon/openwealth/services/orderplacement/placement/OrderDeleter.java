package com.acltabontabon.openwealth.services.orderplacement.placement;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.services.DeleteCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class OrderDeleter extends DeleteCommand<Result<Order>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.OrderPlacement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String clientOrderId;

    @Override
    protected Result<Order> execute() {
        try {
            Order order = restClient.delete()
                .uri(builder -> builder.path(apiProperties.getOrder()).build(clientOrderId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .retrieve()
                .body(Order.class);

            return Result.success(order);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to delete order placement", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
