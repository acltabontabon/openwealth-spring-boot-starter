package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerCreator extends CreateAsyncCommand<CustomerResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final Customer customer;
    private final String correlationId;

    @Override
    protected CustomerResponse execute() {
        try {
            return restClient.post()
                .uri(apiProperties.getCreateCustomerDetails())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(this.customer)
                .retrieve()
                .body(CustomerResponse.class);
        } catch (Exception e) {
            log.error("Failed to create customer", e);
            throw new RuntimeException("Failed to create customer", e);
        }
    }
}
