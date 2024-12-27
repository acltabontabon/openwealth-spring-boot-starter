package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.dtos.ContactResponse;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactCreator extends CreateCommand<OperationResult<ContactResponse>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private final Contact newContact;

    @Override
    protected OperationResult<ContactResponse> execute() {
        try {
            ContactResponse response = restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonContacts()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newContact)
                .retrieve()
                .body(ContactResponse.class);

            return OperationResult.success(response);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to create contact details", e.getStatusMessage());
        }
    }
}
