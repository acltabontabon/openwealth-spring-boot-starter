package com.acltabontabon.openwealth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openwealth.api")
public class OpenWealthApiProperties {

    private String baseUrl;
    private String accessToken;
    private CustomerManagement customerManagement;

    @Data
    public static class CustomerManagement {
        private String customers;
        private String customer;
        private String customerDetails;

        private String prospectPreCheck;
        private String prospectPreCheckStatus;
    }

}
