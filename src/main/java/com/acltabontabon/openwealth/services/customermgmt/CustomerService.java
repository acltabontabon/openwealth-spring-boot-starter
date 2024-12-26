package com.acltabontabon.openwealth.services.customermgmt;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.services.customermgmt.customer.CustomerReader;
import com.acltabontabon.openwealth.services.customermgmt.prospect.PreCheckCreator;
import com.acltabontabon.openwealth.services.customermgmt.prospect.PreCheckStatusReader;
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
    private final CustomerManagementResourcePaths apiProperties;


    /**
     * Return the list of customers.
     *
     * @return CustomerQuery object
     */
    public CustomerReader customers() {
        return new CustomerReader(openWealthRestClient, apiProperties);
    }

    /**
     * Conducts a pre-check at the custody bank.
     *
     * @return PreCheckRequest object
     */
    public PreCheckCreator preCheck() {
        return new PreCheckCreator(openWealthRestClient, apiProperties);
    }

    /**
     * Return the status of the pre-check.
     *
     * @return PreCheckStatusQuery object
     */
    public PreCheckStatusReader preCheckStatus() {
        return new PreCheckStatusReader(openWealthRestClient, apiProperties);
    }
}
