package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.customer.CustomerService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties(OpenWealthApiProperties.class)
public class CustomerManagementConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestClient openWealthRestClient(OpenWealthApiProperties openWealthApiProperties) {
        return RestClient.builder()
            .baseUrl(openWealthApiProperties.getBaseUrl())
            .defaultHeader("Authorization", "Bearer " + openWealthApiProperties.getAccessToken())
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomerService customerService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties) {
        return new CustomerService(openWealthRestClient, openWealthApiProperties.getCustomerManagement());
    }
}
