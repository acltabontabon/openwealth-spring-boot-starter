package com.acltabontabon.openwealth.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openwealth.api")
public class OpenWealthApiProperties {

    private String baseUrl;
    private String accessToken;
    private CustomerManagementResourcePaths customerManagementResourcePaths;

    @Data
    public static class CustomerManagementResourcePaths {
        private String newCustomerDetails;
        private String customers;
        private String customer;
        private String customerDetails;

        private String newCustomerDocument;
        private String customerDocuments;
        private String customerDocument;
        private String customerDocumentDetails;

        private String newPersonDetails;
        private String persons;
        private String personDetails;
        private String person;

        private String personContacts;
        private String personContact;

        private String personAddresses;
        private String personAddress;

        private String personKyc;

        private String prospectPreCheck;
        private String prospectPreCheckStatus;
    }
}
