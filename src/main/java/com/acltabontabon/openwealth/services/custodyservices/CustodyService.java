package com.acltabontabon.openwealth.services.custodyservices;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.customer.CustomerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustodyService {

    private final RestClient openWealthRestClient;
    private final ApiProperties.CustodyServices apiProperties;

    public CustomerReader customers() {
        return new CustomerReader(openWealthRestClient, apiProperties);
    }

}
