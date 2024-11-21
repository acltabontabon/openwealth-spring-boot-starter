package com.acltabontabon.openwealth.service;

import com.acltabontabon.openwealth.model.Customer;
import com.acltabontabon.openwealth.dto.CustomerResponse;
import com.acltabontabon.openwealth.properties.OpenWealthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

@Slf4j
public class CustomerManager {

    private final RestClient restClient;

    public CustomerManager(OpenWealthProperties properties) {
        this.restClient = RestClient.builder()
            .baseUrl(properties.getCustomerManagement().getCustomersEndpoint())
            .defaultHeader("Authorization", "Bearer " + properties.getAccessToken())
            .build();
    }

    public CustomerFinder customers() {
        return new CustomerFinder();
    }

    public class CustomerFinder {

        private String correlationId;
        private String customerId;

        public CustomerFinder withCorrelationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public CustomerFinder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerResponse fetch() {
            return StringUtils.hasText(this.customerId)
                ? fetchSingleCustomer()
                : fetchAllCustomers();
        }

        private CustomerResponse fetchSingleCustomer() {
            Customer customer = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{customerId}").build(this.customerId))
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Correlation-ID", this.correlationId)
                .retrieve()
                .body(Customer.class);

            return CustomerResponse.builder()
                .customer(customer)
                .build();
        }

        private CustomerResponse fetchAllCustomers() {
            return restClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Correlation-ID", this.correlationId)
                .retrieve()
                .body(CustomerResponse.class);
        }
    }
}
