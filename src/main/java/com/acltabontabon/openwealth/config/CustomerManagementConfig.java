package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.customer.CustomerService;
import com.acltabontabon.openwealth.services.prospect.ProspectService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties(OpenWealthApiProperties.class)
public class CustomerManagementConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestClient openWealthRestClient(RestClient.Builder builder, OpenWealthApiProperties openWealthApiProperties) {
        return builder
            .baseUrl(openWealthApiProperties.getBaseUrl())
            .defaultHeaders(header -> header.add("Accept", MediaType.APPLICATION_JSON_VALUE))
            .defaultHeader("Authorization", "Bearer " + openWealthApiProperties.getAccessToken())
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomerService customerService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties) {
        return new CustomerService(openWealthRestClient, openWealthApiProperties.getCustomerManagement());
    }

    @Bean
    @ConditionalOnMissingBean
    public ProspectService prospectService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties) {
        return new ProspectService(openWealthRestClient, openWealthApiProperties.getCustomerManagement());
    }
}
