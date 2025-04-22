package com.acltabontabon.openwealth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openwealth.api")
public class OpenWealthApiProperties {

    private String baseUrl;
    private String accessToken;
    private CustodyServices custodyServices;
    private CustomerManagement customerManagement;
    private OrderPlacement orderPlacement;

    @Data
    public static class CustomerManagement {
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

        private String requestStatus;
    }

    @Data
    public static class CustodyServices {
        private String customers;
        private String customer;

        private String customerPositionStatement;
        private String customerTransactionStatement;

        private String accountPositionStatement;
        private String accountTransactionStatement;

        private String positionTransactionStatement;
    }

    @Data
    public static class OrderPlacement {
        private String orders;
        private String order;

        private String accountAccesses;
    }
}
