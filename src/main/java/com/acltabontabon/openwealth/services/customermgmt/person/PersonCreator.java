package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.PersonResponse;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class PersonCreator extends CreateCommand<PersonResponse> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;
    private final Person personToAssociate;

    @Override
    protected PersonResponse execute() {
        try {
            return restClient.post()
                .uri(apiProperties.getNewPersonDetails())
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
