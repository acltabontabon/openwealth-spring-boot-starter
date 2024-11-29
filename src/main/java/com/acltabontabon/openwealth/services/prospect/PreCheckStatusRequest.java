package com.acltabontabon.openwealth.services.prospect;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.PreCheckResponse;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
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

    public PreCheckResponse fetch() {
        return restClient.get()
            .uri(apiProperties.getProspectPreCheckStatus(), this.temporaryId)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .retrieve()
            .body(PreCheckResponse.class);
    }
}
