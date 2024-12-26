package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.services.DeleteCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class ContactDeleter extends DeleteCommand {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    @Override
    protected Void execute() {
        try {
            restClient.delete()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .toBodilessEntity();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete contact details", e);
        }

        return null;
    }
}
