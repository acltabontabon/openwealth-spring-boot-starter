package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.properties.OpenWealthProperties;
import com.acltabontabon.openwealth.service.CustomerManager;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource("classpath:openwealth.properties")
@EnableConfigurationProperties(OpenWealthProperties.class)
public class CustomerManagementConfig {

    @Bean
    @ConditionalOnMissingBean
    public CustomerManager customerManager(OpenWealthProperties openWealthProperties) {
        return new CustomerManager(openWealthProperties);
    }
}
