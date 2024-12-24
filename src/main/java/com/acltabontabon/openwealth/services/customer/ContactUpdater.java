package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.ApiResponse;
import com.acltabontabon.openwealth.dtos.ContactResponse;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactUpdater extends CreateAsyncCommand<ApiResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String contactId;
    private final String correlationId;

    private final Contact updatedContact;

    @Override
    protected ApiResponse execute() {
        try {
            restClient.put()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(updatedContact)
                .retrieve()
                .toBodilessEntity();

            return new GenericResponse<>();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update contact details", e);
        }
    }
}
