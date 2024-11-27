package com.acltabontabon.openwealth.services.customer;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final RestClient openWealthRestClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    public CustomerListRequest customers() {
        return new CustomerListRequest(openWealthRestClient, apiProperties);
    }

    public CustomerCreator createCustomer() {
        return new CustomerCreator();
    }

    public class CustomerCreator {
        // TODO; Implement this
    }
}
