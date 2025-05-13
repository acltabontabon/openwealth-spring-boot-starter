package com.acltabontabon.openwealth.interceptors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Constants;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

@ExtendWith(MockitoExtension.class)
class CorrelationIdRequestInterceptorTest {

    private static final String TEST_CORRELATION_ID = "test-correlation-id";
    private static final String TEST_URI = "http://test.com/api";
    private static final byte[] TEST_BODY = "test-body".getBytes(StandardCharsets.UTF_8);

    @Mock
    private HttpRequest request;

    @Mock
    private ClientHttpRequestExecution execution;

    @Mock
    private ClientHttpResponse response;

    @Mock
    private HttpHeaders headers;

    @Captor
    private ArgumentCaptor<HttpRequest> requestCaptor;

    @Captor
    private ArgumentCaptor<byte[]> bodyCaptor;

    private CorrelationIdRequestInterceptor interceptor;

    @BeforeEach
    void setUp() {
        interceptor = new CorrelationIdRequestInterceptor();
        when(request.getHeaders()).thenReturn(headers);
        when(request.getURI()).thenReturn(URI.create(TEST_URI));
    }

    @AfterEach
    void tearDown() {
        MDC.clear();
    }

    @Test
    void shouldUseCorrelationIdFromMDC() throws IOException {
        MDC.put(Constants.MDC_CORRELATION_ID, TEST_CORRELATION_ID);
        when(execution.execute(requestCaptor.capture(), bodyCaptor.capture())).thenReturn(response);

        ClientHttpResponse result = interceptor.intercept(request, TEST_BODY, execution);

        assertEquals(response, result);
        verify(headers).set(Constants.HEADER_CORRELATION_ID, TEST_CORRELATION_ID);
        assertEquals(TEST_BODY, bodyCaptor.getValue());
    }

    @Test
    void shouldUseCorrelationIdFromRequestHeaders() throws IOException {
        when(headers.getFirst(Constants.HEADER_CORRELATION_ID)).thenReturn(TEST_CORRELATION_ID);
        when(execution.execute(requestCaptor.capture(), bodyCaptor.capture())).thenReturn(response);

        ClientHttpResponse result = interceptor.intercept(request, TEST_BODY, execution);

        assertEquals(response, result);
        verify(headers).set(Constants.HEADER_CORRELATION_ID, TEST_CORRELATION_ID);
        assertEquals(TEST_BODY, bodyCaptor.getValue());
    }

    @Test
    void shouldGenerateNewCorrelationIdWhenNotPresent() throws IOException {
        when(execution.execute(requestCaptor.capture(), bodyCaptor.capture())).thenReturn(response);

        ClientHttpResponse result = interceptor.intercept(request, TEST_BODY, execution);

        assertEquals(response, result);

        ArgumentCaptor<String> correlationIdCaptor = ArgumentCaptor.forClass(String.class);
        verify(headers).set(eq(Constants.HEADER_CORRELATION_ID), correlationIdCaptor.capture());

        String capturedCorrelationId = correlationIdCaptor.getValue();
        assertNotNull(capturedCorrelationId);

        assertEquals(TEST_BODY, bodyCaptor.getValue());
    }
}
