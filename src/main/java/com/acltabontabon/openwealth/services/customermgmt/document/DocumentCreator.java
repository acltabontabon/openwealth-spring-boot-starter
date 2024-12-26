package com.acltabontabon.openwealth.services.customermgmt.document;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.ApiResponse;
import com.acltabontabon.openwealth.dtos.ContactResponse;
import com.acltabontabon.openwealth.models.Document;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class DocumentCreator extends CreateCommand<ApiResponse> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    private final Document newDocument;

    @Override
    protected ApiResponse execute() {
        try {
            return restClient.post()
                .uri(builder -> builder.path(apiProperties.getNewCustomerDocument()).build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newDocument)
                .retrieve()
                .body(ContactResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add document", e);
        }
    }
}
