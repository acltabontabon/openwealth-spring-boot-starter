package com.acltabontabon.openwealth.configs;

import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.interceptors.CorrelationIdRequestInterceptor;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.properties.OpenWealthAsyncProperties;
import com.acltabontabon.openwealth.security.StaticTokenProvider;
import com.acltabontabon.openwealth.security.TokenProvider;
import com.acltabontabon.openwealth.services.custodyservices.CustodyService;
import com.acltabontabon.openwealth.services.custodyservices.CustodyServicesComponentFactory;
import com.acltabontabon.openwealth.services.custodyservices.CustodyServicesComponentFactoryImpl;
import com.acltabontabon.openwealth.services.customermgmt.CustomerManagementComponentFactory;
import com.acltabontabon.openwealth.services.customermgmt.CustomerManagementComponentFactoryImpl;
import com.acltabontabon.openwealth.services.customermgmt.CustomerService;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementComponentFactory;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementComponentFactoryImpl;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties({
    OpenWealthApiProperties.class,
    OpenWealthAsyncProperties.class
})
public class OpenWealthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TokenProvider tokenProvider(OpenWealthApiProperties openWealthApiProperties) {
        log.debug("Initializing tokenProvider bean");
        return new StaticTokenProvider(openWealthApiProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestClient openWealthRestClient(RestClient.Builder builder, OpenWealthApiProperties openWealthApiProperties, TokenProvider tokenProvider) {
        log.debug("Initializing openWealthRestClient bean");
        return builder
            .baseUrl(openWealthApiProperties.getBaseUrl())
            .requestInterceptor(new CorrelationIdRequestInterceptor())
            .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new FailedRequestException(response.getStatusText(), response.getStatusCode());
            })
            .defaultHeader("Authorization", "Bearer " + tokenProvider.getAccessToken())
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public TaskExecutor openwealthTaskExecutor(OpenWealthAsyncProperties openWealthAsyncProperties) {
        log.debug("Initializing openwealthTaskExecutor bean");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(openWealthAsyncProperties.getCorePoolSize());
        executor.setMaxPoolSize(openWealthAsyncProperties.getMaxPoolSize());
        executor.setQueueCapacity(openWealthAsyncProperties.getQueueCapacity());
        executor.setThreadNamePrefix(openWealthAsyncProperties.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }

    // Factory beans
    @Bean
    @ConditionalOnMissingBean
    public CustomerManagementComponentFactory customerManagementComponentFactory(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing customerManagementComponentFactory bean");
        return new CustomerManagementComponentFactoryImpl(openWealthRestClient, openWealthApiProperties.getCustomerManagement(), openwealthTaskExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public CustodyServicesComponentFactory custodyServicesComponentFactory(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing custodyServicesComponentFactory bean");
        return new CustodyServicesComponentFactoryImpl(openWealthRestClient, openWealthApiProperties.getCustodyServices(), openwealthTaskExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public OrderPlacementComponentFactory orderPlacementComponentFactory(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing orderPlacementComponentFactory bean");
        return new OrderPlacementComponentFactoryImpl(openWealthRestClient, openWealthApiProperties.getOrderPlacement(), openwealthTaskExecutor);
    }

    // Service beans
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "openwealth.customer", name = "enabled", havingValue = "true")
    public CustomerService customerService(CustomerManagementComponentFactory componentFactory) {
        log.debug("Initializing customerService bean");
        return new CustomerService(componentFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "openwealth.custody", name = "enabled", havingValue = "true")
    public CustodyService custodyService(CustodyServicesComponentFactory componentFactory) {
        log.debug("Initializing custodyService bean");
        return new CustodyService(componentFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "openwealth.order", name = "enabled", havingValue = "true")
    public OrderPlacementService orderPlacementService(OrderPlacementComponentFactory componentFactory) {
        log.debug("Initializing orderPlacementService bean");
        return new OrderPlacementService(componentFactory);
    }
}
