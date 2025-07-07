package com.acltabontabon.openwealth.services.customermgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Constants;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.services.TestFixtures;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec uriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Mock
    private CustomerManagement apiProperties;

    @Mock
    private TaskExecutor asyncExecutor;

    @Captor
    private ArgumentCaptor<Consumer<HttpHeaders>> headersConsumerCaptor;

    @InjectMocks
    private CustomerService customerService;

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetCorrelationIdAndLimitInHeaders() {
        CustomerResponse expectedResponse = new CustomerResponse();

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(String.class)))
            .thenReturn(uriSpec);
        when(uriSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(uriSpec);
        when(uriSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerResponse.class))
            .thenReturn(expectedResponse);

        customerService.customers()
            .withCorrelationId(TestFixtures.TEST_CORRELATION_ID)
            .withLimit(TestFixtures.TEST_LIMIT)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(TestFixtures.TEST_CORRELATION_ID, headers.getFirst(Constants.HEADER_CORRELATION_ID));
        assertEquals(String.valueOf(TestFixtures.TEST_LIMIT), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchCustomersSuccessfully() {
        CustomerResponse expectedResponse = new CustomerResponse();

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(String.class)))
            .thenReturn(uriSpec);
        when(uriSpec.headers(any(Consumer.class)))
            .thenReturn(uriSpec);
        when(uriSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerResponse.class))
            .thenReturn(expectedResponse);

        Result<CustomerResponse> result = customerService.customers()
            .fetch();

        assertTrue(result.isSuccess());
        assertEquals(expectedResponse, result.getData());
        assertEquals("Operation successful", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleFailedRequest() {
        String errorMessage = "Failed to fetch list of customers";

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(String.class)))
            .thenReturn(uriSpec);
        when(uriSpec.headers(any(Consumer.class)))
            .thenReturn(uriSpec);
        when(uriSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerResponse.class))
            .thenThrow(new FailedRequestException("Error message", HttpStatus.INTERNAL_SERVER_ERROR));

        Result<CustomerResponse> result = customerService.customers()
            .fetch();

        assertFalse(result.isSuccess());
        assertNull(result.getData());
        assertEquals(errorMessage, result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNullResponse() {
        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(String.class)))
            .thenReturn(uriSpec);
        when(uriSpec.headers(any(Consumer.class)))
            .thenReturn(uriSpec);
        when(uriSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerResponse.class))
            .thenReturn(null);

        Result<CustomerResponse> result = customerService.customers()
            .fetch();

        assertTrue(result.isSuccess());
        assertNull(result.getData());
        assertEquals("Operation successful", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchCustomerWithCompleteDetails() {
        Customer expectedCustomer = Customer.builder()
            .customerId("customer_002")
            .name("Test Customer")
            .build();

        String customerDetailsUrl = "/customers/{customerId}/details";

        lenient().when(apiProperties.getCustomerDetails())
            .thenReturn(customerDetailsUrl);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(uriSpec);
        when(uriSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(uriSpec);
        when(uriSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Customer.class))
            .thenReturn(expectedCustomer);

        Result<Customer> result = customerService.customers()
            .withCorrelationId(TestFixtures.TEST_CORRELATION_ID)
            .withCustomerId("customer_002")
            .completeDetails()
            .fetch();

        assertTrue(result.isSuccess());
        assertEquals(expectedCustomer, result.getData());
        assertEquals("Operation successful", result.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);
        assertEquals(TestFixtures.TEST_CORRELATION_ID, headers.getFirst(Constants.HEADER_CORRELATION_ID));
    }
}
