package com.acltabontabon.openwealth.services.orderplacement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Constants;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.orderplacement.Order;
import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.OrderPlacement;
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
class OrderPlacementServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private OrderPlacement apiProperties;

    @Mock
    private TaskExecutor asyncExecutor;

    @Mock
    private RestClient.RequestHeadersUriSpec uriSpec;

    @Mock
    private RestClient.RequestBodyUriSpec bodyUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec headersSpec;

    @Mock
    private RestClient.RequestBodySpec bodySpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Captor
    private ArgumentCaptor<Consumer<HttpHeaders>> headersConsumerCaptor;

    @InjectMocks
    private OrderPlacementService orderPlacementService;

    @Test
    void shouldCreateNewOrder() {
        Order expectedOrder = Order.builder().build();
        RequestedOrder requestedOrder = TestFixtures.createTestRequestedOrder();

        when(apiProperties.getOrders())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.post())
            .thenReturn(bodyUriSpec);
        when(bodyUriSpec.uri(anyString()))
            .thenReturn(bodySpec);
        when(bodySpec.headers(any(Consumer.class)))
            .thenReturn(bodySpec);
        when(bodySpec.body(any(RequestedOrder.class)))
            .thenReturn(bodySpec);
        when(bodySpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenReturn(expectedOrder);

        Result<Order> result = orderPlacementService.orders()
            .withCorrelationId("test-correlation-id")
            .createNew(requestedOrder)
            .submit();

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(expectedOrder, result.getData());
    }

    @Test
    void shouldHandleErrorWhenCreatingNewOrder() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        RequestedOrder requestedOrder = TestFixtures.createTestRequestedOrder();

        when(apiProperties.getOrders())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.post())
            .thenReturn(bodyUriSpec);
        when(bodyUriSpec.uri(anyString()))
            .thenReturn(bodySpec);
        when(bodySpec.headers(any(Consumer.class)))
            .thenReturn(bodySpec);
        when(bodySpec.body(any(RequestedOrder.class)))
            .thenReturn(bodySpec);
        when(bodySpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenThrow(new FailedRequestException("Failed to create order", statusCode));

        Result<Order> result = orderPlacementService.orders()
            .withCorrelationId("test-correlation-id")
            .createNew(requestedOrder)
            .submit();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to create order placement", result.getMessage());
    }

    @Test
    void shouldSetCorrelationIdHeaderWhenCreatingOrder() {
        Order expectedOrder = Order.builder().build();
        RequestedOrder requestedOrder = TestFixtures.createTestRequestedOrder();
        String correlationId = "test-correlation-id";

        when(apiProperties.getOrders())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.post())
            .thenReturn(bodyUriSpec);
        when(bodyUriSpec.uri(anyString()))
            .thenReturn(bodySpec);
        when(bodySpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(bodySpec);
        when(bodySpec.body(any(RequestedOrder.class)))
            .thenReturn(bodySpec);
        when(bodySpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenReturn(expectedOrder);

        Result<Order> result = orderPlacementService.orders()
            .withCorrelationId(correlationId)
            .createNew(requestedOrder)
            .submit();

        assertTrue(result.isSuccess());

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);
        assertEquals(correlationId, headers.getFirst(Constants.HEADER_CORRELATION_ID));
    }
    @Test
    void shouldCancelOrder() {
        Order expectedOrder = Order.builder().build();
        String clientOrderId = "XR002";

        lenient().when(apiProperties.getOrder())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.delete())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenReturn(expectedOrder);

        Result<Order> result = orderPlacementService.orders()
            .withClientOrderId(clientOrderId)
            .cancel()
            .submit();

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(expectedOrder, result.getData());
    }

    @Test
    void shouldHandleErrorWhenCancelingOrder() {
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        String clientOrderId = "XR002";

        lenient().when(apiProperties.getOrder())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.delete())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(any(Consumer.class)))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenThrow(new FailedRequestException("Failed to delete order", statusCode));

        Result<Order> result = orderPlacementService.orders()
            .withCorrelationId("test-correlation-id")
            .withClientOrderId(clientOrderId)
            .cancel()
            .submit();

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertEquals("Failed to delete order placement", result.getMessage());
    }

    @Test
    void shouldSetCorrelationIdHeaderWhenCancelingOrder() {
        Order expectedOrder = Order.builder().build();
        String clientOrderId = "XR002";
        String correlationId = "test-correlation-id";

        lenient().when(apiProperties.getOrder())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.delete())
            .thenReturn(uriSpec);
        when(uriSpec.uri(any(Function.class)))
            .thenReturn(headersSpec);
        when(headersSpec.headers(headersConsumerCaptor.capture()))
            .thenReturn(headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(Order.class))
            .thenReturn(expectedOrder);

        Result<Order> result = orderPlacementService.orders()
            .withCorrelationId(correlationId)
            .withClientOrderId(clientOrderId)
            .cancel()
            .submit();

        assertTrue(result.isSuccess());

        HttpHeaders headers = new HttpHeaders();
        headersConsumerCaptor.getValue().accept(headers);
        assertEquals(correlationId, headers.getFirst(Constants.HEADER_CORRELATION_ID));
    }
}
