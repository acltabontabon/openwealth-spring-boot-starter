package com.acltabontabon.openwealth.services.customermgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.configs.ApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private CustomerManagement apiProperties;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(restClient, apiProperties);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnListOfCustomers() {
        List<Customer> customers = List.of(Customer.builder().build());
        CustomerResponse customerResponse = CustomerResponse.builder().customers(customers).build();
        OperationResult<CustomerResponse> expectedResponse = OperationResult.success(customerResponse);
        String mockEndpoint = "http://mock-api/customers";

        RestClient.RequestHeadersUriSpec<?> uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec<?> headersSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(mockEndpoint);
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

        OperationResult<CustomerResponse> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .fetch();

        assertEquals(expectedResponse.getData().getCustomers().size(), actualResponse.getData().getCustomers().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnOneCustomer() {
        Customer customer = Customer.builder().customerId("1").build();
        OperationResult<Customer> expectedResponse = OperationResult.success(customer);

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

        OperationResult<Customer> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .withCustomerId("4321")
            .fetch();

        assertNotNull(actualResponse.getData());
        assertEquals(expectedResponse.getData().getCustomerId(), actualResponse.getData().getCustomerId());
    }
}