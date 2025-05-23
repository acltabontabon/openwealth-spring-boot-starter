package com.acltabontabon.openwealth.services.customermgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.services.TestFixtures;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private CustomerManagement apiProperties;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnListOfCustomers() {
        List<Customer> customers = List.of(Customer.builder().build());
        CustomerResponse customerResponse = CustomerResponse.builder().customers(customers).build();
        Result<CustomerResponse> expectedResponse = Result.success(customerResponse);

        RestClient.RequestHeadersUriSpec<?> uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenAnswer(invocation -> uriSpec);
        when(uriSpec.uri(anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.header(anyString(), anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerResponse.class))
            .thenAnswer(invocation -> customerResponse);

        Result<CustomerResponse> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .fetch();

        assertEquals(expectedResponse.getData().getCustomers().size(), actualResponse.getData().getCustomers().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnOneCustomer() {
        Customer customer = Customer.builder().customerId("1").build();
        Result<Customer> expectedResponse = Result.success(customer);

        RestClient.RequestHeadersUriSpec<?> uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        when(restClient.get())
            .thenAnswer(invocation -> uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.header(anyString(), anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Customer.class))
            .thenAnswer(invocation -> customer);

        Result<Customer> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .withCustomerId("4321")
            .fetch();

        assertNotNull(actualResponse.getData());
        assertEquals(expectedResponse.getData().getCustomerId(), actualResponse.getData().getCustomerId());
    }
}