package com.acltabontabon.openwealth.services.customermgmt.prospect;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.dtos.ProspectResponse;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class PreCheckStatusReader extends ReadCommand<Result<ProspectResponse>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String temporaryId;
    private String correlationId;

    public PreCheckStatusReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    protected Result<ProspectResponse> execute()  {
        try {
            ProspectResponse prospectResponse = restClient.get()
                .uri(apiProperties.getProspectPreCheckStatus(), this.temporaryId)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(ProspectResponse.class);

            return Result.success(prospectResponse);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch prospect pre-check status", e.getStatusMessage());
        }
    }
}
