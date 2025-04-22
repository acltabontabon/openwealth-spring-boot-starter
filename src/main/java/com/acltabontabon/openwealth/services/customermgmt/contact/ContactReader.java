package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactReader extends ReadCommand<Result<List<Contact>>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    public SingleContactReader withContactId(String contactId) {
        return new SingleContactReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, contactId);
    }

    @Override
    protected Result<List<Contact>> execute() {
        try {
            List<Contact> contacts = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContacts()).build(customerId, personId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(contacts);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch contact details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
