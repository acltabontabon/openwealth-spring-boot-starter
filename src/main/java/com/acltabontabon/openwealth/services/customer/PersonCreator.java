package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.PersonResponse;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class PersonCreator extends CreateAsyncCommand<PersonResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final Person personToAssociate;

    private final String customerId;
    private final String correlationId;

    // FIXME: Think of a way to standardized the response of this method at the moment, it returns
    //  the person that was associated to the customer which is coming from the API as well
    @Override
    protected PersonResponse execute() {
        try {
            return restClient.post()
                .uri(apiProperties.getCreatePersonDetails())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(this.personToAssociate)
                .retrieve()
                .body(PersonResponse.class);
        } catch (Exception e) {
            log.error("Failed to associate person to customer: {}", this.customerId, e);
            throw new RuntimeException("Failed to associate person to customer: " + this.customerId, e);
        }
    }
}
