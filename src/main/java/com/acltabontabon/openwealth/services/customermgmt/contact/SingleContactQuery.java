package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.services.QueryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleContactQuery extends QueryCommand<GenericResponse<Contact>> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    @Override
    protected GenericResponse<Contact> execute() {
        try {
            Contact contact = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Contact.class);

            return new GenericResponse<>(contact);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person contact details",e);
        }
    }
}

