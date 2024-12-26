package com.acltabontabon.openwealth.configs;

import com.acltabontabon.openwealth.services.custody.CustodyService;
import com.acltabontabon.openwealth.services.customermgmt.CustomerService;
import com.acltabontabon.openwealth.services.order.OrderPlacementService;
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
public class OpenWealthAutoConfiguration {

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
        return new CustomerService(openWealthRestClient, openWealthApiProperties.getCustomerManagementResourcePaths());
    }

    @Bean
    @ConditionalOnMissingBean
    public CustodyService custodyService() {
        return new CustodyService();
    }

    @Bean
    @ConditionalOnMissingBean
    public OrderPlacementService orderPlacementService() {
        return new OrderPlacementService();
    }
}
