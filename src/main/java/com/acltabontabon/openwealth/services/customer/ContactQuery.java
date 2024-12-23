package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactQuery extends QueryAsyncCommand<List<Contact>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String correlationId;

    public SingleContactQuery withContactId(String contactId) {
        return new SingleContactQuery(restClient, apiProperties, customerId, personId, contactId, this.correlationId);
    }

    @Override
    protected List<Contact> execute() {
        try {
            return restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonContacts()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person details",e);
        }
    }
}
