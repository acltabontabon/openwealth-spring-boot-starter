package com.acltabontabon.openwealth.services.custodyservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Constants;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustodyServices;
import com.acltabontabon.openwealth.services.TestFixtures;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
class CustodyServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private CustodyServices apiProperties;

    @Mock
    private TaskExecutor asyncExecutor;

    @Captor
    private ArgumentCaptor<Consumer<HttpHeaders>> headersConsumerCaptor;

    @InjectMocks
    private CustodyService custodyService;

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnListOfCustomers() {
        var customers = List.of(Customer.builder().build());
        var expectedResponse = Result.success(customers);

        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenAnswer(invocation -> uriSpec);
        when(uriSpec.uri(anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.headers(any()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(new ParameterizedTypeReference<List<Customer>>() {}))
            .thenReturn(customers);

        var actualResponse = custodyService.customers().fetch();

        assertTrue(actualResponse.isSuccess());
        assertNotNull(actualResponse.getData());
        assertEquals(expectedResponse.getData().size(), actualResponse.getData().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingCustomersWithLimit() {
        int limit = 10;
        var customers = List.of(Customer.builder().build());

        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(anyString()))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class)))
            .thenReturn(customers);

        custodyService.customers()
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetCorrelationIdHeaderWhenFetchingCustomersWithCorrelationId() {
        var customers = List.of(Customer.builder().build());

        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(anyString()))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class)))
            .thenReturn(customers);

        custodyService.customers()
            .withCorrelationId("1234")
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals("1234", headers.getFirst(Constants.HEADER_CORRELATION_ID));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchSingleCustomerWithCustomerId() {
        String customerId = "customer_001";
        Customer customer = Customer.builder()
            .customerIdentification(customerId)
            .customerReferenceCurrency("CHF")
            .build();

        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(java.util.function.Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Customer.class))
            .thenReturn(customer);

        Result<Customer> result = custodyService.customers()
            .withCustomerId(customerId)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(customerId, result.getData().getCustomerIdentification());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleErrorWhenFetchingCustomers() {
        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(anyString()))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(any(ParameterizedTypeReference.class)))
            .thenThrow(new FailedRequestException("Failed to fetch customers", statusCode));

        Result<List<Customer>> result = custodyService.customers().fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch list of customers", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchCustomersAsynchronously() {
        var successConsumer = mock(Consumer.class);
        var errorConsumer = mock(Consumer.class);

        custodyService.customers()
            .fetchAsync(successConsumer, errorConsumer);

        verify(asyncExecutor).execute(any());
    }
}
