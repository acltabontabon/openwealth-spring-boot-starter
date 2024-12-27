package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class DocumentReader extends ReadCommand<OperationResult<List<Contact>>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    @Override
    protected OperationResult<List<Contact>> execute() {
        try {
            List<Contact> contacts = restClient.get()
                .uri(builder -> builder.path(apiProperties.getCustomerDocuments()).build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return OperationResult.success(contacts);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch list of documents", e.getStatusMessage());
        }
    }

    public SingleDocumentReader withDocumentId(String documentId) {
        return new SingleDocumentReader(restClient, apiProperties, correlationId, customerId, documentId);
    }
}
