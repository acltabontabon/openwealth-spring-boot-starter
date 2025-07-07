package com.acltabontabon.openwealth.services.customermgmt.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.models.customermgmt.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentCreator;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentReader;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonCreator;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerReader extends ReadCommand<Result<Customer>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;

    private boolean completeDetails;

    public SingleCustomerReader completeDetails() {
        completeDetails = true;
        return this;
    }

    public PersonReader associatedPersons() {
        return new PersonReader(restClient, apiProperties, asyncExecutor, customerId, correlationId);
    }

    public PersonCreator addPerson(Person personToAssociate) {
        return new PersonCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, personToAssociate);
    }

    public DocumentReader documents() {
        return new DocumentReader(restClient, apiProperties, asyncExecutor, correlationId, customerId);
    }

    public DocumentCreator addDocument(Document document) {
        return new DocumentCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, document);
    }

    @Override
    protected Result<Customer> execute() {
        try {
            Customer customer = restClient.get()
                .uri(builder -> {
                    if (completeDetails) {
                        return builder.path(apiProperties.getCustomerDetails()).build(customerId);
                    } else {
                        return builder.path(apiProperties.getCustomer()).build(customerId);
                    }
                })
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                })
                .retrieve()
                .body(Customer.class);

            return Result.success(customer);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch customer details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}

