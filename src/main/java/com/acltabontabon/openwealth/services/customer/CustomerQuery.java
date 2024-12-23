package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.CustomerApiResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerQuery extends QueryAsyncCommand<CustomerApiResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String correlationId;

    public CustomerQuery withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleCustomerQuery withCustomerId(String customerId) {
        return new SingleCustomerQuery(restClient, apiProperties, customerId, this.correlationId);
    }

    public CustomerCreator createNew(Customer customer) {
        return new CustomerCreator(restClient, apiProperties, customer, this.correlationId);
    }

    @Override
    protected CustomerApiResponse execute() {
        try {
            return restClient.get()
                .uri(apiProperties.getCustomers())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(CustomerApiResponse.class);
        } catch (Exception e) {
            log.error("Failed to fetch customers", e);
            throw new RuntimeException("Failed to fetch customers", e);
        }
    }
}
