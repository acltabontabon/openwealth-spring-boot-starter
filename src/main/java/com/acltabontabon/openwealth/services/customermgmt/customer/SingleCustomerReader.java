package com.acltabontabon.openwealth.services.customermgmt.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.models.Document;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentCreator;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentReader;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonCreator;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonReader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerReader extends ReadCommand<GenericResponse<Customer>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;

    private boolean completeDetails;

    public SingleCustomerReader completeDetails() {
        this.completeDetails = true;
        return this;
    }

    public PersonReader associatedPersons() {
        return new PersonReader(restClient, apiProperties, customerId, correlationId);
    }

    public PersonCreator addPerson(Person personToAssociate) {
        return new PersonCreator(restClient, apiProperties, correlationId, customerId, personToAssociate);
    }

    public DocumentReader documents() {
        return new DocumentReader(restClient, apiProperties, correlationId, customerId);
    }

    public DocumentCreator addDocument(Document document) {
        return new DocumentCreator(restClient, apiProperties, correlationId, customerId, document);
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

