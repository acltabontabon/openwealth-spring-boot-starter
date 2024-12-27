package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleContactReader extends ReadCommand<Result<Contact>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    @Override
    protected Result<Contact> execute() {
        try {
            Contact contact = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Contact.class);

            return Result.success(contact);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch person contact details", e.getStatusMessage());
        }
    }
}

