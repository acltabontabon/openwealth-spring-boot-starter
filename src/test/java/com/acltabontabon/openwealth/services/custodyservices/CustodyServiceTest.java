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
import com.acltabontabon.openwealth.models.custodyservices.AccountPositionStatement;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.models.custodyservices.CustomerPositionStatement;
import com.acltabontabon.openwealth.models.custodyservices.TransactionStatement;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustodyServices;
import com.acltabontabon.openwealth.services.TestFixtures;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
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

    @Mock
    private RestClient.RequestHeadersUriSpec uriSpec;

    @Mock
    private RestClient.RequestHeadersSpec headersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Captor
    private ArgumentCaptor<Consumer<HttpHeaders>> headersConsumerCaptor;

    @InjectMocks
    private CustodyService custodyService;

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnListOfCustomers() {
        var customers = List.of(Customer.builder().build());
        var expectedResponse = Result.success(customers);

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

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
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

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchCustomerPositionStatement() {
        CustomerPositionStatement positionStatement = CustomerPositionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerPositionStatement.class))
            .thenReturn(positionStatement);

        Result<CustomerPositionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(positionStatement, result.getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNotFoundErrorWhenFetchingPositionStatement() {
        var statusCode = HttpStatus.NOT_FOUND;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerPositionStatement.class))
            .thenThrow(new FailedRequestException("Failed to fetch position statement", statusCode));

        Result<CustomerPositionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch customer's position statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleServerErrorWhenFetchingPositionStatement() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerPositionStatement.class))
            .thenThrow(new FailedRequestException("Internal server error", statusCode));

        Result<CustomerPositionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch customer's position statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingPositionStatementWithLimit() {
        int limit = 10;
        CustomerPositionStatement positionStatement = CustomerPositionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(CustomerPositionStatement.class))
            .thenReturn(positionStatement);

        custodyService.customers()
            .withCustomerId("customer_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingAccountPositionStatementWithLimit() {
        int limit = 10;
        AccountPositionStatement positionStatement = AccountPositionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(AccountPositionStatement.class))
            .thenReturn(positionStatement);

        custodyService.customers()
            .withAccountId("account_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchAccountPositionStatement() {
        AccountPositionStatement positionStatement = AccountPositionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(AccountPositionStatement.class))
            .thenReturn(positionStatement);

        Result<AccountPositionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(positionStatement, result.getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNotFoundErrorWhenFetchingAccountPositionStatement() {
        var statusCode = HttpStatus.NOT_FOUND;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(AccountPositionStatement.class))
            .thenThrow(new FailedRequestException("Failed to fetch account position statement", statusCode));

        Result<AccountPositionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account position statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleServerErrorWhenFetchingAccountPositionStatement() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(AccountPositionStatement.class))
            .thenThrow(new FailedRequestException("Internal server error", statusCode));

        Result<AccountPositionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .positionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account position statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchCustomerTransactionStatement() {
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        Result<TransactionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(transactionStatement, result.getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNotFoundErrorWhenFetchingTransactionStatement() {
        var statusCode = HttpStatus.NOT_FOUND;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Failed to fetch transaction statement", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch customer transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleServerErrorWhenFetchingTransactionStatement() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Internal server error", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withCustomerId("customer_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch customer transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingTransactionStatementWithLimit() {
        int limit = 10;
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        custodyService.customers()
            .withCustomerId("customer_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchAccountTransactionStatement() {
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        Result<TransactionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(transactionStatement, result.getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNotFoundErrorWhenFetchingAccountTransactionStatement() {
        var statusCode = HttpStatus.NOT_FOUND;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Failed to fetch account transaction statement", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleServerErrorWhenFetchingAccountTransactionStatement() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Internal server error", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withAccountId("account_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingAccountTransactionStatementWithLimit() {
        int limit = 10;
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        custodyService.customers()
            .withAccountId("account_001")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldFetchPositionTransactionStatement() {
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        Result<TransactionStatement> result = custodyService.customers()
            .withPositionId("159447")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(transactionStatement, result.getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleNotFoundErrorWhenFetchingPositionTransactionStatement() {
        var statusCode = HttpStatus.NOT_FOUND;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Failed to fetch position transaction statement", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withPositionId("159447")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldHandleServerErrorWhenFetchingPositionTransactionStatement() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenThrow(new FailedRequestException("Internal server error", statusCode));

        Result<TransactionStatement> result = custodyService.customers()
            .withPositionId("159447")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .fetch();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to fetch account transaction statement", result.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldSetLimitHeaderWhenFetchingPositionTransactionStatementWithLimit() {
        int limit = 10;
        TransactionStatement transactionStatement = TransactionStatement.builder().build();

        when(restClient.get())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(TransactionStatement.class))
            .thenReturn(transactionStatement);

        custodyService.customers()
            .withPositionId("159447")
            .transactionStatement(LocalDate.of(2023, Month.MAY, 1), true, DateType.TRANSACTION_DATE)
            .withLimit(limit)
            .fetch();

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);

        assertEquals(String.valueOf(limit), headers.getFirst(Constants.HEADER_LIMIT));
    }
}
