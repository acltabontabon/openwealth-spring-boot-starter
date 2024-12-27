package com.acltabontabon.openwealth.services.customermgmt.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerReader extends ReadCommand<Result<CustomerResponse>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private String correlationId;

    public CustomerReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleCustomerReader withCustomerId(String customerId) {
        return new SingleCustomerReader(restClient, apiProperties, customerId, this.correlationId);
    }

    public CustomerCreator createNew(Customer customer) {
        return new CustomerCreator(restClient, apiProperties, this.correlationId, customer);
    }

    @Override
    protected Result<CustomerResponse> execute() {
        try {
            CustomerResponse response = restClient.get()
                .uri(apiProperties.getCustomers())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(CustomerResponse.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch list of customers", e.getStatusMessage());
        }
    }
}
