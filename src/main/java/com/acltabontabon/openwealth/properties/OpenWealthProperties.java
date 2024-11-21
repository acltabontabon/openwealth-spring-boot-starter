package com.acltabontabon.openwealth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openwealth.api")
public class OpenWealthProperties {

    private String baseUrl;
    private String accessToken;
    private CustomerManagement customerManagement;

    @Data
    public static class CustomerManagement {
        private String baseUrl;
        private String customersEndpoint;
    }

}
