package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
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

    public KycQuery kycDetails() {
        return new KycQuery(restClient, apiProperties, customerId, personId, correlationId);
    }

    public ContactQuery contactDetails() {
        return new ContactQuery(restClient, apiProperties, customerId, personId, correlationId);
    }

    public KycCreator addKycDetails(Kyc kycToAdd) {
        return new KycCreator(restClient, apiProperties, customerId, personId, correlationId, kycToAdd);
    }

    @Override
    protected Person execute() {
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

