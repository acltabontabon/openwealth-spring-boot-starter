package com.acltabontabon.openwealth.services.customermgmt.prospect;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.ProspectResponse;
import com.acltabontabon.openwealth.services.QueryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PreCheckStatusQuery extends QueryCommand<ProspectResponse> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private String correlationId;
    private String temporaryId;

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
