package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressCreator;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressReader;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycCreator;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycReader;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactCreator;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactDeleter;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactReader;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePersonRead extends ReadCommand<GenericResponse<Person>> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private boolean completeDetails;

    public SinglePersonRead completeDetails() {
        this.completeDetails = true;
        return this;
    }

    public KycReader kycDetails() {
        return new KycReader(restClient, apiProperties, correlationId, customerId, personId);
    }

    public KycCreator addKycDetails(Kyc newKyc) {
        return new KycCreator(restClient, apiProperties, correlationId, customerId, personId, newKyc);
    }

    public ContactReader contactDetails() {
        return new ContactReader(restClient, apiProperties, correlationId, customerId, personId);
    }

    public ContactCreator addContactDetails(Contact newContact) {
        return new ContactCreator(restClient, apiProperties, correlationId, customerId, personId, newContact);
    }

    public ContactUpdater updateContactDetails(String contactId, Contact updatedContact) {
        return new ContactUpdater(restClient, apiProperties, correlationId, customerId, personId, contactId, updatedContact);
    }

    public ContactDeleter deleteContactDetails(String contactId) {
        return new ContactDeleter(restClient, apiProperties, correlationId, customerId, personId, contactId);
    }

    public AddressReader addressDetails() {
        return new AddressReader(restClient, apiProperties, correlationId, customerId, personId);
    }

    public AddressCreator addAddressDetails(Address newAddress) {
        return new AddressCreator(restClient, apiProperties, correlationId, customerId, personId, newAddress);
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

