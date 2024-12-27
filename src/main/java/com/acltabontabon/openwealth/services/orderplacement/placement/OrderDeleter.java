package com.acltabontabon.openwealth.services.orderplacement.placement;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.services.DeleteCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class OrderDeleter extends DeleteCommand<Result<Order>> {

    private final RestClient restClient;
    private final ApiProperties.OrderPlacement apiProperties;

    private final String correlationId;
    private final String clientOrderId;

    @Override
    protected Result<Order> execute() {
        try {
            Order order = restClient.delete()
                .uri(builder -> builder.path(apiProperties.getOrder()).build(this.clientOrderId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Order.class);

            return Result.success(order);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to delete order placement", e.getStatusMessage());
        }
    }
}
