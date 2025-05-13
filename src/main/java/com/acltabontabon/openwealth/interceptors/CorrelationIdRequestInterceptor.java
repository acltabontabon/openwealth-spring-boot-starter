package com.acltabontabon.openwealth.interceptors;

import com.acltabontabon.openwealth.commons.Constants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class CorrelationIdRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = MDC.get(Constants.MDC_CORRELATION_ID);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = request.getHeaders().getFirst(Constants.HEADER_CORRELATION_ID);

            // If not in request headers, generate a new one
            if (correlationId == null || correlationId.isEmpty()) {
                log.debug("No correlation ID found in MDC or request headers. Creating a new one.");
                correlationId = UUID.randomUUID().toString();
            } else {
                log.debug("Using correlation ID from request headers: {}", correlationId);
            }
        } else {
            log.debug("Using correlation ID from MDC: {}", correlationId);
        }

        request.getHeaders().set(Constants.HEADER_CORRELATION_ID, correlationId);

        log.debug("Request URI: {}", request.getURI());
        log.debug("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        log.debug("Correlation ID: {}", correlationId);

        return execution.execute(request, body);
    }
}
