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

    public CustomerQuery customers() {
        return new CustomerQuery(openWealthRestClient, apiProperties);
    }
}
