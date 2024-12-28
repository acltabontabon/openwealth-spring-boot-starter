package com.acltabontabon.openwealth.services.customermgmt.status;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.RequestStatusResponse;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class RequestStatusReader extends ReadCommand<Result<RequestStatusResponse>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String temporaryId;

    private String correlationId;

    public RequestStatusReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    protected Result<RequestStatusResponse> execute()  {
        try {
            RequestStatusResponse response = restClient.get()
                .uri(apiProperties.getRequestStatus(), this.temporaryId)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(RequestStatusResponse.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch request status", e.getStatusMessage());
        }
    }
}
