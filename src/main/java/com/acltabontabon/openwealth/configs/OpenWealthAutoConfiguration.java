package com.acltabontabon.openwealth.configs;

import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.interceptors.CorrelationIdInterceptor;
import com.acltabontabon.openwealth.services.custodyservices.CustodyService;
import com.acltabontabon.openwealth.services.customermgmt.CustomerService;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties(ApiProperties.class)
public class OpenWealthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RestClient openWealthRestClient(RestClient.Builder builder, ApiProperties apiProperties) {
        log.debug("Initializing openWealthRestClient bean");
        return builder
            .baseUrl(apiProperties.getBaseUrl())
            .requestInterceptor(new CorrelationIdInterceptor())
            .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new FailedRequestException(response.getStatusText(), response.getStatusCode());
            })
            .defaultHeader("Authorization", "Bearer " + apiProperties.getAccessToken())
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomerService customerService(RestClient openWealthRestClient, ApiProperties apiProperties) {
        log.debug("Initializing customerService bean");
        return new CustomerService(openWealthRestClient, apiProperties.getCustomerManagement());
    }

    @Bean
    @ConditionalOnMissingBean
    public CustodyService custodyService(RestClient openWealthRestClient, ApiProperties apiProperties) {
        log.debug("Initializing custodyService bean");
        return new CustodyService(openWealthRestClient, apiProperties.getCustodyServices());
    }

    @Bean
    @ConditionalOnMissingBean
    public OrderPlacementService orderPlacementService(RestClient openWealthRestClient, ApiProperties apiProperties) {
        log.debug("Initializing orderPlacementService bean");
        return new OrderPlacementService(openWealthRestClient, apiProperties.getOrderPlacement());
    }
}
