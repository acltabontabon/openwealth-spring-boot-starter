package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.CustomerResponse;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.AsyncApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerListRequest extends AsyncApi<CustomerResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String correlationId;

    public CustomerListRequest withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleCustomerRequest withCustomerId(String customerId) {
        return new SingleCustomerRequest(restClient, apiProperties, customerId, this.correlationId);
    }

    public CustomerResponse fetch() {
        return execute();
    }

    @Override
    protected CustomerResponse execute() {
        try {
            return restClient.get()
                .uri(apiProperties.getCustomers())
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(CustomerResponse.class);
        } catch (Exception e) {
            log.error("Failed to fetch customers", e);
            throw new RuntimeException("Failed to fetch customers", e);
        }
    }
}
