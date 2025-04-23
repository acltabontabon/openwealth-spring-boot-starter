package com.acltabontabon.openwealth.configs;

import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.interceptors.CorrelationIdInterceptor;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.properties.OpenWealthAsyncProperties;
import com.acltabontabon.openwealth.security.StaticTokenProvider;
import com.acltabontabon.openwealth.security.TokenProvider;
import com.acltabontabon.openwealth.services.custodyservices.CustodyService;
import com.acltabontabon.openwealth.services.customermgmt.CustomerService;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
            .requestInterceptor(new CorrelationIdInterceptor())
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

    @Bean
    @ConditionalOnMissingBean
    public CustomerService customerService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing customerService bean");
        return new CustomerService(openWealthRestClient, openWealthApiProperties.getCustomerManagement(), openwealthTaskExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public CustodyService custodyService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing custodyService bean");
        return new CustodyService(openWealthRestClient, openWealthApiProperties.getCustodyServices(), openwealthTaskExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public OrderPlacementService orderPlacementService(RestClient openWealthRestClient, OpenWealthApiProperties openWealthApiProperties, TaskExecutor openwealthTaskExecutor) {
        log.debug("Initializing orderPlacementService bean");
        return new OrderPlacementService(openWealthRestClient, openWealthApiProperties.getOrderPlacement(), openwealthTaskExecutor);
    }
}
