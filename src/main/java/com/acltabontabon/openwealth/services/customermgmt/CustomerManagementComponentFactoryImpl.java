package com.acltabontabon.openwealth.services.customermgmt;

import com.acltabontabon.openwealth.models.customermgmt.Address;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.models.customermgmt.Kyc;
import com.acltabontabon.openwealth.models.customermgmt.Person;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressCreator;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressReader;
import com.acltabontabon.openwealth.services.customermgmt.address.AddressUpdater;
import com.acltabontabon.openwealth.services.customermgmt.address.SingleAddressRead;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactCreator;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactDeleter;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactReader;
import com.acltabontabon.openwealth.services.customermgmt.contact.ContactUpdater;
import com.acltabontabon.openwealth.services.customermgmt.contact.SingleContactReader;
import com.acltabontabon.openwealth.services.customermgmt.customer.CustomerCreator;
import com.acltabontabon.openwealth.services.customermgmt.customer.CustomerReader;
import com.acltabontabon.openwealth.services.customermgmt.customer.SingleCustomerReader;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentCreator;
import com.acltabontabon.openwealth.services.customermgmt.document.DocumentReader;
import com.acltabontabon.openwealth.services.customermgmt.document.SingleDocumentReader;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycCreator;
import com.acltabontabon.openwealth.services.customermgmt.kyc.KycReader;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonCreator;
import com.acltabontabon.openwealth.services.customermgmt.person.PersonReader;
import com.acltabontabon.openwealth.services.customermgmt.person.SinglePersonReader;
import com.acltabontabon.openwealth.services.customermgmt.prospect.PreCheckCreator;
import com.acltabontabon.openwealth.services.customermgmt.prospect.PreCheckStatusReader;
import com.acltabontabon.openwealth.services.customermgmt.status.RequestStatusReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CustomerManagementComponentFactoryImpl implements CustomerManagementComponentFactory {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    @Override
    public CustomerReader createCustomerReader() {
        return new CustomerReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public PreCheckCreator createPreCheckCreator() {
        return new PreCheckCreator(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public PreCheckStatusReader createPreCheckStatusReader(String temporaryId) {
        return new PreCheckStatusReader(restClient, apiProperties, asyncExecutor, temporaryId);
    }

    @Override
    public RequestStatusReader createRequestStatusReader(String temporaryId) {
        return new RequestStatusReader(restClient, apiProperties, asyncExecutor, temporaryId);
    }

    @Override
    public SingleCustomerReader createSingleCustomerReader(String customerId, String correlationId) {
        return new SingleCustomerReader(restClient, apiProperties, asyncExecutor, customerId, correlationId);
    }

    @Override
    public CustomerCreator createCustomerCreator(String correlationId, Customer customer) {
        return new CustomerCreator(restClient, apiProperties, asyncExecutor, correlationId, customer);
    }

    @Override
    public PersonReader createPersonReader(String customerId, String correlationId) {
        return new PersonReader(restClient, apiProperties, asyncExecutor, customerId, correlationId);
    }

    @Override
    public SinglePersonReader createSinglePersonReader(String correlationId, String customerId, String personId) {
        return new SinglePersonReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId);
    }

    @Override
    public PersonCreator createPersonCreator(String correlationId, String customerId, Person person) {
        return new PersonCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, person);
    }

    @Override
    public DocumentReader createDocumentReader(String correlationId, String customerId) {
        return new DocumentReader(restClient, apiProperties, asyncExecutor, correlationId, customerId);
    }

    @Override
    public SingleDocumentReader createSingleDocumentReader(String correlationId, String customerId, String documentId) {
        return new SingleDocumentReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, documentId);
    }

    @Override
    public DocumentCreator createDocumentCreator(String correlationId, String customerId, Document document) {
        return new DocumentCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, document);
    }

    @Override
    public KycReader createKycReader(String correlationId, String customerId, String personId) {
        return new KycReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId);
    }

    @Override
    public KycCreator createKycCreator(String correlationId, String customerId, String personId, Kyc kyc) {
        return new KycCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, kyc);
    }

    @Override
    public ContactReader createContactReader(String correlationId, String customerId, String personId) {
        return new ContactReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId);
    }

    @Override
    public SingleContactReader createSingleContactReader(String correlationId, String customerId, String personId, String contactId) {
        return new SingleContactReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, contactId);
    }

    @Override
    public ContactCreator createContactCreator(String correlationId, String customerId, String personId, Contact contact) {
        return new ContactCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, contact);
    }

    @Override
    public ContactUpdater createContactUpdater(String correlationId, String customerId, String personId, String contactId, Contact contact) {
        return new ContactUpdater(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, contactId, contact);
    }

    @Override
    public ContactDeleter createContactDeleter(String correlationId, String customerId, String personId, String contactId) {
        return new ContactDeleter(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, contactId);
    }

    @Override
    public AddressReader createAddressReader(String correlationId, String customerId, String personId) {
        return new AddressReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId);
    }

    @Override
    public SingleAddressRead createSingleAddressReader(String correlationId, String customerId, String personId, String addressId) {
        return new SingleAddressRead(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, addressId);
    }

    @Override
    public AddressCreator createAddressCreator(String correlationId, String customerId, String personId, Address address) {
        return new AddressCreator(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, address);
    }

    @Override
    public AddressUpdater createAddressUpdater(String correlationId, String customerId, String personId, String addressId, Address address) {
        return new AddressUpdater(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, addressId, address);
    }
}