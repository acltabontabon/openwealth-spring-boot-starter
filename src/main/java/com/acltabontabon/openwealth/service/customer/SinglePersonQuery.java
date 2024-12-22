package com.acltabontabon.openwealth.service.customer;

import static com.acltabontabon.openwealth.config.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.model.Person;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.service.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePersonQuery extends QueryAsyncCommand<Person> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String correlationId;

    private boolean completeDetails;

    public SinglePersonQuery completeDetails() {
        this.completeDetails = true;
        return this;
    }

    @Override
    public Person execute() {
        try {
            return restClient.get()
                .uri(builder -> {
                    if (this.completeDetails) {
                        return builder.path(apiProperties.getPersonDetails()).build(this.customerId, this.personId);
                    } else {
                        return builder.path(apiProperties.getPerson()).build(this.customerId, this.personId);
                    }
                })
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Person.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person details",e);
        }
    }
}

