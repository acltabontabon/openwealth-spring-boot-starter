package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PersonReader extends ReadCommand<OperationResult<List<Person>>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    public SinglePersonReader withPersonId(String personId) {
        return new SinglePersonReader(restClient, apiProperties, this.correlationId, customerId, personId);
    }

    @Override
    protected OperationResult<List<Person>> execute() {
        try {
            List<Person> personList = restClient.get()
                .uri(apiProperties.getPersons(), this.customerId)
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return OperationResult.success(personList);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch list of associated persons", e.getStatusMessage());
        }
    }
}
