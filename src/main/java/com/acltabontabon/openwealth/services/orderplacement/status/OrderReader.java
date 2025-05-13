package com.acltabontabon.openwealth.services.orderplacement.status;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;
import static com.acltabontabon.openwealth.commons.Constants.HEADER_LIMIT;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.orderplacement.placement.OrderCreator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class OrderReader extends ReadCommand<Result<List<Order>>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.OrderPlacement apiProperties;
    private final TaskExecutor asyncExecutor;

    private String correlationId;
    private Integer limit;

    public OrderReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public OrderReader withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public SingleOrderReader withClientOrderId(String clientOrderId) {
        return new SingleOrderReader(restClient, apiProperties, asyncExecutor, correlationId, clientOrderId);
    }

    public OrderCreator createNew(RequestedOrder order) {
        return new OrderCreator(restClient, apiProperties, asyncExecutor, correlationId, order);
    }

    @Override
    protected Result<List<Order>> execute() {
        try {
            List<Order> response = restClient.get()
                .uri(apiProperties.getOrders())
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                    if (limit != null && limit > 0) {
                        headers.set(HEADER_LIMIT, String.valueOf(limit));
                    }
                })
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of orders", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
