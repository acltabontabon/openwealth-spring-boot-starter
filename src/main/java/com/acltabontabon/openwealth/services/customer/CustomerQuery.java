package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerQuery extends QueryAsyncCommand<GenericResponse<List<Customer>>> {

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
    protected GenericResponse<List<Customer>> execute() {
        try {
            List<Customer> customers = restClient.get()
                .uri(apiProperties.getCustomers())
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return new GenericResponse<>(customers);
        } catch (Exception e) {
            log.error("Failed to fetch customers", e);
            throw new RuntimeException("Failed to fetch customers", e);
        }
    }
}
