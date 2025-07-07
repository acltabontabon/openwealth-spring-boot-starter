package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleDocumentReader extends ReadCommand<Result<Document>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;
    private final String documentId;

    private boolean completeDetails;

    private SingleDocumentReader completeDetails() {
        completeDetails = true;
        return this;
    }

    @Override
    protected Result<Document> execute() {
        try {
            Document contact = restClient.get()
                .uri(builder -> {
                    if (completeDetails) {
                        return builder.path(apiProperties.getCustomerDocumentDetails()).build(customerId, documentId);
                    } else {
                        return builder.path(apiProperties.getCustomerDocument()).build(customerId, documentId);
                    }
                })
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                })
                .retrieve()
                .body(Document.class);

            return Result.success(contact);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch document details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}

