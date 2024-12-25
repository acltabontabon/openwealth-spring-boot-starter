package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PersonQuery extends QueryAsyncCommand<GenericResponse<List<Person>>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String correlationId;

    public SinglePersonQuery withPersonId(String personId) {
        return new SinglePersonQuery(restClient, apiProperties, customerId, personId, this.correlationId);
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
