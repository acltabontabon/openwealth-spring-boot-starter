package com.acltabontabon.openwealth.services.customermgmt.person;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.models.Contact;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.models.Person;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressCreator;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressReader;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressUpdater;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactCreator;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactDeleter;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactReader;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactUpdater;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycCreator;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycReader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePersonReader extends ReadCommand<OperationResult<Person>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private boolean completeDetails;

    public SinglePersonReader completeDetails() {
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

    public AddressUpdater updateAddressDetails(String addressId, Address updatedAddress) {
        return new AddressUpdater(restClient, apiProperties, correlationId, customerId, personId, addressId, updatedAddress);
    }

    @Override
    protected OperationResult<Person> execute() {
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

            return OperationResult.success(person);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch person details", e.getStatusMessage());
        }
    }
}

