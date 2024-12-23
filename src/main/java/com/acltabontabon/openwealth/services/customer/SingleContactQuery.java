package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleContactQuery extends QueryAsyncCommand<Contact> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String contactId;
    private final String correlationId;

    @Override
    protected Contact execute() {
        try {
            return restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Contact.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person contact details",e);
        }
    }
}

