package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.CustomerCreateResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerCreator extends CreateAsyncCommand<CustomerCreateResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final Customer customer;
    private final String correlationId;

    public CustomerCreateResponse submit() {
        return execute();
    }

    @Override
    protected CustomerCreateResponse execute() {
        try {
            return restClient.post()
                .uri(apiProperties.getCustomers())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(this.customer)
                .retrieve()
                .body(CustomerCreateResponse.class);
        } catch (Exception e) {
            log.error("Failed to fetch customers", e);
            throw new RuntimeException("Failed to create customer", e);
        }
    }
}
