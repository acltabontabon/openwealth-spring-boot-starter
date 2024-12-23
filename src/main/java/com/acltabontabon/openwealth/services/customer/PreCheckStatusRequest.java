package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.PreCheckApiResponse;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PreCheckStatusRequest {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String temporaryId;
    private String correlationId;

    public PreCheckStatusRequest withTemporaryId(String temporaryId) {
        this.temporaryId = temporaryId;
        return this;
    }

    public PreCheckStatusRequest withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public PreCheckApiResponse fetch() {
        return restClient.get()
            .uri(apiProperties.getProspectPreCheckStatus(), this.temporaryId)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .retrieve()
            .body(PreCheckApiResponse.class);
    }
}
