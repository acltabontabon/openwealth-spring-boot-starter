package com.acltabontabon.openwealth.services.customermgmt.prospect;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.ProspectResponse;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Prospect;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Conduct a pre-check to validate if the prospect might be accepted as customer by the custody
 * bank based on base parameters like domicile / nationality etc.
 */
@RequiredArgsConstructor
public class PreCheckCreator extends CreateCommand<Result<ProspectResponse>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private String correlationId;
    private Prospect prospect;

    public PreCheckCreator withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public PreCheckCreator prospect(Prospect prospect) {
        this.prospect = prospect;
        return this;
    }

    @Override
    protected Result<ProspectResponse> execute() {
        try {
            ProspectResponse prospectResponse = restClient.post()
                .uri(apiProperties.getProspectPreCheck())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(prospect)
                .retrieve()
                .body(ProspectResponse.class);

            return Result.success(prospectResponse);
        } catch (FailedRequestException e) {
            return Result.failure(e.getStatusMessage(), String.valueOf(e.getStatusCode()));
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
