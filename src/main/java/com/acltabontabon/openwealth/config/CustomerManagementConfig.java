package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.properties.OpenWealthProperties;
import com.acltabontabon.openwealth.service.CustomerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@Slf4j
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
