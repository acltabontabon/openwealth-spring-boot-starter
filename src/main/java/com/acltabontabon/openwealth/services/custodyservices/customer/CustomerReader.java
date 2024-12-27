package com.acltabontabon.openwealth.services.custodyservices.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerReader extends ReadCommand<Result<List<Customer>>> {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private String correlationId;

    public CustomerReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleCustomerReader withCustomerId(String customerId) {
        return new SingleCustomerReader(restClient, apiProperties, this.correlationId, customerId);
    }

    public SingleAccountReader withAccountId(String accountId) {
        return new SingleAccountReader(restClient, apiProperties, correlationId, accountId);
    }

    public SinglePositionReader withPositionId(String positionId) {
        return new SinglePositionReader(restClient, apiProperties, correlationId, positionId);
    }


    @Override
    protected Result<List<Customer>> execute() {
        try {
            List<Customer> response = restClient.get()
                .uri(apiProperties.getCustomers())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of customers", e.getStatusMessage());
        }
    }
}
