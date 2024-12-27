package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleDocumentReader extends ReadCommand<OperationResult<Document>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String documentId;

    private boolean completeDetails;

    private SingleDocumentReader completeDetails() {
        this.completeDetails = true;
        return this;
    }

    @Override
    protected OperationResult<Document> execute() {
        try {
            Document contact = restClient.get()
                .uri(builder -> {
                    if (this.completeDetails) {
                        return builder.path(apiProperties.getCustomerDocumentDetails()).build(this.customerId, this.documentId);
                    } else {
                        return builder.path(apiProperties.getCustomerDocument()).build(this.customerId, this.documentId);
                    }
                })
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Document.class);

            return OperationResult.success(contact);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch document details", e.getStatusMessage());
        }
    }
}

