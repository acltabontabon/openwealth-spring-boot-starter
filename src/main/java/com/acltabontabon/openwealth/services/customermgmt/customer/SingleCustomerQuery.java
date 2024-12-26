package com.acltabontabon.openwealth.services.customermgmt.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.models.Document;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentCreator;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentQuery;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonCreator;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerQuery extends QueryAsyncCommand<GenericResponse<Customer>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String correlationId;

    private boolean completeDetails;

    public SingleCustomerQuery completeDetails() {
        this.completeDetails = true;
        return this;
    }

    public PersonQuery associatedPersons() {
        return new PersonQuery(restClient, apiProperties, customerId, correlationId);
    }

    public PersonCreator addPerson(Person personToAssociate) {
        return new PersonCreator(restClient, apiProperties, personToAssociate, customerId, correlationId);
    }

    public DocumentQuery documents() {
        return new DocumentQuery(restClient, apiProperties, customerId, correlationId);
    }

    public DocumentCreator addDocument(Document document) {
        return new DocumentCreator(restClient, apiProperties, customerId, correlationId, document);
    }

    @Override
    protected GenericResponse<Customer> execute() {
        try {
            Customer customer = restClient.get()
                .uri(builder -> {
                    if (this.completeDetails) {
                        return builder.path(apiProperties.getCustomerDetails()).build(this.customerId);
                    } else {
                        return builder.path(apiProperties.getCustomer()).build(this.customerId);
                    }
                })
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Customer.class);

            return new GenericResponse<>(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer",e);
        }
    }
}

