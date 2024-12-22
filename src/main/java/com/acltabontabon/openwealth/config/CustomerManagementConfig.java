package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.service.customer.CustomerService;
import com.acltabontabon.openwealth.service.prospect.ProspectService;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties(OpenWealthApiProperties.class)
public class CustomerManagementConfig {

    @Bean
    @ConditionalOnMissingBean
    public RestClient openWealthRestClient(RestClient.Builder builder, OpenWealthApiProperties openWealthApiProperties) {
        return builder
            .baseUrl(openWealthApiProperties.getBaseUrl())
            .requestInterceptor((request, body, execution) -> {
                log.debug("Request URI: {}", request.getURI());
                log.debug("Request Body: {}", new String(body, StandardCharsets.UTF_8));

                return execution.execute(request, body);
            })
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
