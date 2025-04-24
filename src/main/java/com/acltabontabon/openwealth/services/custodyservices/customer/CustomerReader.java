package com.acltabontabon.openwealth.services.custodyservices.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;
import static com.acltabontabon.openwealth.commons.Constants.HEADER_LIMIT;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerReader extends ReadCommand<Result<List<Customer>>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private String correlationId;
    private Integer limit;

    public CustomerReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public CustomerReader withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public SingleCustomerReader withCustomerId(String customerId) {
        return new SingleCustomerReader(restClient, apiProperties, asyncExecutor, correlationId, customerId);
    }

    public SingleAccountReader withAccountId(String accountId) {
        return new SingleAccountReader(restClient, apiProperties, asyncExecutor, correlationId, accountId);
    }

    public SinglePositionReader withPositionId(String positionId) {
        return new SinglePositionReader(restClient, apiProperties, asyncExecutor, correlationId, positionId);
    }

    @Override
    protected Result<List<Customer>> execute() {
        try {
            List<Customer> response = restClient.get()
                .uri(apiProperties.getCustomers())
                .headers((headers) -> {
                    if (limit != null && limit > 0) {
                        headers.set(HEADER_LIMIT, String.valueOf(limit));
                    }
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                })
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of customers", e.getStatusCode().toString());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
