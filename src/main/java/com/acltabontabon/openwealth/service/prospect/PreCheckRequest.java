package com.acltabontabon.openwealth.service.prospect;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.PreCheckResponse;
import com.acltabontabon.openwealth.model.Prospect;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PreCheckRequest {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String correlationId;
    private Prospect prospect;

    public PreCheckRequest withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public PreCheckRequest prospect(Prospect prospect) {
        this.prospect = prospect;
        return this;
    }

    public PreCheckResponse submit() {
        return restClient.post()
            .uri(apiProperties.getProspectPreCheck())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .body(prospect)
            .retrieve()
            .body(PreCheckResponse.class);
    }
}
