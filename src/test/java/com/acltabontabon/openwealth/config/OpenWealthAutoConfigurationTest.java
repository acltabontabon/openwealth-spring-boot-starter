package com.acltabontabon.openwealth.config;

import com.acltabontabon.openwealth.configs.OpenWealthAutoConfiguration;
import com.acltabontabon.openwealth.security.TokenProvider;
import com.acltabontabon.openwealth.services.custodyservices.CustodyService;
import com.acltabontabon.openwealth.services.customermgmt.CustomerService;
import com.acltabontabon.openwealth.services.orderplacement.OrderPlacementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;


public class OpenWealthAutoConfigurationTest
{

    @Mock
    private RestClient restClient;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private TaskExecutor taskExecutor;

    @Mock
    CustomerService customerService;

    @Mock
    CustodyService custodyService;

    @Mock
    OrderPlacementService orderPlacementService;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(OpenWealthAutoConfiguration.class))
            .withBean(RestClient.Builder.class, RestClient::builder);


    //When property is true

    @Test
    void custodyServiceShouldBeCreatedWhenEnabled() {
        contextRunner
                .withPropertyValues("openwealth.custody.enabled=true")
                .run(context -> assertThat(context).hasSingleBean(CustodyService.class));
    }

    @Test
    void customerServiceShouldBeCreatedWhenEnabled()
    {
        contextRunner
                .withPropertyValues("openwealth.customer.enabled=true")
                .run(context -> assertThat(context).hasSingleBean(CustomerService.class));
    }

    @Test
    void orderServiceShouldBeCreatedWhenEnabled()
    {
        contextRunner
                .withPropertyValues("openwealth.order.enabled=true")
                .run(context -> assertThat(context).hasSingleBean(OrderPlacementService.class));
    }



    //When Property is false

    @Test
    void custodyServiceShouldNotBeCreatedWhenDisabled() {
        contextRunner
                .withPropertyValues("openwealth.custody.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(CustodyService.class));
    }

    @Test
    void customerServiceShouldNotBeCreatedWhenDisabled()
    {
        contextRunner
                .withPropertyValues("openwealth.customer.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(CustomerService.class));
    }

    @Test
    void orderServiceShouldNotBeCreatedWhenDisabled()
    {
        contextRunner
                .withPropertyValues("openwealth.order.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(OrderPlacementService.class));
    }



    // When Property is Empty
    @Test
    void custodyServiceShouldNotBeCreatedWhenEmpty() {
        contextRunner
                .run(context -> assertThat(context).doesNotHaveBean(CustodyService.class));
    }

    @Test
    void customerServiceShouldNotBeCreatedWhenEmpty()
    {
        contextRunner.run(context -> assertThat(context).doesNotHaveBean(CustomerService.class));
    }

    @Test
    void orderServiceShouldNotBeCreatedWhenEmpty()
    {
        contextRunner
                .run(context -> assertThat(context).doesNotHaveBean(OrderPlacementService.class));
    }


}
