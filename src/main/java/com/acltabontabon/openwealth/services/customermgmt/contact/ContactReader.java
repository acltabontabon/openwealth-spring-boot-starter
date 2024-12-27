package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactReader extends ReadCommand<OperationResult<List<Contact>>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    public SingleContactReader withContactId(String contactId) {
        return new SingleContactReader(restClient, apiProperties, this.correlationId, customerId, personId, contactId);
    }

    @Override
    protected OperationResult<List<Contact>> execute() {
        try {
            List<Contact> contacts = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContacts()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return OperationResult.success(contacts);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch contact details", e.getStatusMessage());
        }
    }
}
