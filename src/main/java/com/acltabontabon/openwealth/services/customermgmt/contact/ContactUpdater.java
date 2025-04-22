package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.services.UpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactUpdater extends UpdateCommand<Result<Void>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    private final Contact updatedContact;

    @Override
    protected Result<Void> execute() {
        try {
            restClient.put()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(customerId, personId, contactId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .body(updatedContact)
                .retrieve()
                .toBodilessEntity();

            return Result.success(null);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to update contact details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
