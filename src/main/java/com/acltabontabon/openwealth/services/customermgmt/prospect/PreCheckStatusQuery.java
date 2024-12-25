package com.acltabontabon.openwealth.services.customermgmt.prospect;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.ProspectResponse;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PreCheckStatusQuery extends QueryAsyncCommand<ProspectResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String temporaryId;
    private String correlationId;

    public PreCheckStatusQuery withTemporaryId(String temporaryId) {
        this.temporaryId = temporaryId;
        return this;
    }

    public PreCheckStatusQuery withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    protected ProspectResponse execute() {
        return restClient.get()
            .uri(apiProperties.getProspectPreCheckStatus(), this.temporaryId)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .retrieve()
            .body(ProspectResponse.class);
    }
}
