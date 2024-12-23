package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.CustomerApiResponse;
import com.acltabontabon.openwealth.models.Customer;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerQuery extends QueryAsyncCommand<CustomerApiResponse> {

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
    protected CustomerApiResponse execute() {
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

            return CustomerApiResponse.builder()
                .customer(customer)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch customer",e);
        }
    }
}

