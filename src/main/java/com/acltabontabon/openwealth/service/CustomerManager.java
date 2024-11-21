package com.acltabontabon.openwealth.service;

import com.acltabontabon.openwealth.dto.CustomerResponse;
import com.acltabontabon.openwealth.model.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
public class CustomerManager {

    private final static String HEADER_CORRELATION_ID = "X-Correlation-ID";

    private final RestClient restClient;

    public CustomerManager(OpenWealthProperties properties) {
        this.restClient = RestClient.builder()
            .baseUrl(properties.getCustomerManagement().getCustomersEndpoint())
            .defaultHeader("Authorization", "Bearer " + properties.getAccessToken())
            .build();
    }

    public CustomerQuery customers() {
        return new CustomerQuery();
    }

    public CustomerCreator createCustomer() {
        return new CustomerCreator();
    }

    public class CustomerCreator {

    }

    public class CustomerQuery {

        private String correlationId;

        public CustomerQuery withCorrelationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public SpecificCustomerQuery withCustomerId(String customerId) {
            return new SpecificCustomerQuery(customerId, this.correlationId);
        }

        public CustomerResponse fetch() {
            return restClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(CustomerResponse.class);
        }
    }

    @RequiredArgsConstructor
    public class SpecificCustomerQuery {
        private final String customerId;
        private final String correlationId;

        private boolean fullRecord;

        public SpecificCustomerQuery fullRecord() {
            this.fullRecord = true;
            return this;
        }

        public CustomerResponse fetch() {
            Customer customer = restClient.get()
                .uri(uriBuilder -> {
                    if (this.fullRecord) {
                        return uriBuilder.path("/{customerId}/customer-details").build(this.customerId);
                    } else {
                        return uriBuilder.path("/{customerId}").build(this.customerId);
                    }
                })
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Customer.class);

            return CustomerResponse.builder()
                .customer(customer)
                .build();
        }
    }
}
