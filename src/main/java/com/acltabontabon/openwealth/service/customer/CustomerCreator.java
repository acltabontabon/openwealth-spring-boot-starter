package com.acltabontabon.openwealth.service.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.CustomerOperationResponse;
import com.acltabontabon.openwealth.model.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.service.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerCreator extends CreateAsyncCommand<CustomerOperationResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final Customer customer;
    private final String correlationId;

    @Override
    protected CustomerOperationResponse execute() {
        try {
            return restClient.post()
                .uri(apiProperties.getCreateCustomerDetails())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(this.customer)
                .retrieve()
                .body(CustomerOperationResponse.class);
        } catch (Exception e) {
            log.error("Failed to create customer", e);
            throw new RuntimeException("Failed to create customer", e);
        }
    }
}
