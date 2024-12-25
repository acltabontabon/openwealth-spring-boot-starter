package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePersonQuery extends QueryAsyncCommand<GenericResponse<Person>> {

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

    public KycCreator addKycDetails(Kyc newKyc) {
        return new KycCreator(restClient, apiProperties, customerId, personId, correlationId, newKyc);
    }

    public ContactQuery contactDetails() {
        return new ContactQuery(restClient, apiProperties, customerId, personId, correlationId);
    }

    public ContactCreator addContactDetails(Contact newContact) {
        return new ContactCreator(restClient, apiProperties, customerId, personId, correlationId, newContact);
    }

    public ContactUpdater updateContactDetails(String contactId, Contact updatedContact) {
        return new ContactUpdater(restClient, apiProperties, customerId, personId, contactId, correlationId, updatedContact);
    }

    public ContactDeleter deleteContactDetails(String contactId) {
        return new ContactDeleter(restClient, apiProperties, customerId, personId, contactId, correlationId);
    }

    @Override
    protected GenericResponse<Person> execute() {
        try {
            Person person = restClient.get()
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

            return new GenericResponse<>(person);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person details",e);
        }
    }
}

