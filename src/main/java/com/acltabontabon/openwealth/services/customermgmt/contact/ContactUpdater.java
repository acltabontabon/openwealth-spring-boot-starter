package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.UpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactUpdater extends UpdateCommand {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    private final Contact updatedContact;

    @Override
    protected Void execute() {
        try {
            restClient.put()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(updatedContact)
                .retrieve()
                .toBodilessEntity();

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update contact details", e);
        }
    }
}
