package com.acltabontabon.openwealth.services.customer;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

/**
 * CustomerService represents the Customer Management API within the OpenWealth API, enabling
 * connectivity between custody banks and WealthTech platforms like Portfolio Management Systems
 * and CRMs. It streamlines customer onboarding and lifecycle management (e.g., Static Data, KYC,
 * and Documents) by connecting CRMs to custody banks. Users can create, update, and access
 * customer data from custody banks. This focuses exclusively on individuals, excluding
 * companies, complex structures, and products.
 */
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final RestClient openWealthRestClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    public CustomerQuery customers() {
        return new CustomerQuery(openWealthRestClient, apiProperties);
    }

    public PreCheckRequest preCheck() {
        return new PreCheckRequest(openWealthRestClient, apiProperties);
    }

    public PreCheckStatusRequest preCheckStatus() {
        return new PreCheckStatusRequest(openWealthRestClient, apiProperties);
    }
}
