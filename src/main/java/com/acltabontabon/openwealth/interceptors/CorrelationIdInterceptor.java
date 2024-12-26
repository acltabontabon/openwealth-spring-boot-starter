package com.acltabontabon.openwealth.interceptors;

import com.acltabontabon.openwealth.configs.Constants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = request.getHeaders().getFirst(Constants.HEADER_CORRELATION_ID);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
            request.getHeaders().add(Constants.HEADER_CORRELATION_ID, correlationId);
        }

        log.debug("Request URI: {}", request.getURI());
        log.debug("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        log.debug("Assigned Correlation ID: {}", correlationId);

        return execution.execute(request, body);
    }
}
