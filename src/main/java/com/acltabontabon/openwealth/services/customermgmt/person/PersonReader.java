package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PersonReader extends ReadCommand<GenericResponse<List<Person>>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    public SinglePersonReader withPersonId(String personId) {
        return new SinglePersonReader(restClient, apiProperties, this.correlationId, customerId, personId);
    }

    @Override
    protected GenericResponse<List<Person>> execute() {
        List<Person> personList = restClient.get()
            .uri(apiProperties.getPersons(), this.customerId)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});

        return new GenericResponse<>(personList);
    }
}
