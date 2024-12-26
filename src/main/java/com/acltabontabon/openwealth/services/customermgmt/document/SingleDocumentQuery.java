package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Document;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleDocumentQuery extends QueryAsyncCommand<GenericResponse<Document>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String documentId;
    private final String correlationId;

    private boolean completeDetails;

    private SingleDocumentQuery completeDetails() {
        this.completeDetails = true;
        return this;
    }

    @Override
    protected GenericResponse<Document> execute() {
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

            return new GenericResponse<>(contact);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch document",e);
        }
    }
}

