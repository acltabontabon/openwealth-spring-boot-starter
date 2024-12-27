package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.dtos.DocumentResponse;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class DocumentCreator extends CreateCommand<Result<DocumentResponse>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    private final Document newDocument;

    @Override
    protected Result<DocumentResponse> execute() {
        try {
            DocumentResponse response = restClient.post()
                .uri(builder -> builder.path(apiProperties.getNewCustomerDocument()).build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newDocument)
                .retrieve()
                .body(DocumentResponse.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to add document", e.getStatusMessage());
        }
    }
}
