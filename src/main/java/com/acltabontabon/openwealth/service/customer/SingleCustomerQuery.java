package com.acltabontabon.openwealth.service.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dto.CustomerOperationResponse;
import com.acltabontabon.openwealth.model.Customer;
import com.acltabontabon.openwealth.model.Person;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.service.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerQuery extends QueryAsyncCommand<CustomerOperationResponse> {

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

    @Override
    public CustomerOperationResponse execute() {
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

            return CustomerOperationResponse.builder()
                .customer(customer)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer",e);
        }
    }
}

