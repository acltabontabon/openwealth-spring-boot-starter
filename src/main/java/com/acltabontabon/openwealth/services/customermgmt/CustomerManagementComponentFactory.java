package com.acltabontabon.openwealth.services.customermgmt;

import com.acltabontabon.openwealth.models.customermgmt.Address;
import com.acltabontabon.openwealth.models.customermgmt.Contact;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.models.customermgmt.Document;
import com.acltabontabon.openwealth.models.customermgmt.Kyc;
import com.acltabontabon.openwealth.models.customermgmt.Person;
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

public interface CustomerManagementComponentFactory {

    CustomerReader createCustomerReader();
    PreCheckCreator createPreCheckCreator();
    PreCheckStatusReader createPreCheckStatusReader(String temporaryId);
    RequestStatusReader createRequestStatusReader(String temporaryId);

    // Customer-related components
    SingleCustomerReader createSingleCustomerReader(String customerId, String correlationId);
    CustomerCreator createCustomerCreator(String correlationId, Customer customer);

    // Person-related components
    PersonReader createPersonReader(String customerId, String correlationId);
    SinglePersonReader createSinglePersonReader(String correlationId, String customerId, String personId);
    PersonCreator createPersonCreator(String correlationId, String customerId, Person person);

    // Document-related components
    DocumentReader createDocumentReader(String correlationId, String customerId);
    SingleDocumentReader createSingleDocumentReader(String correlationId, String customerId, String documentId);
    DocumentCreator createDocumentCreator(String correlationId, String customerId, Document document);

    // KYC-related components
    KycReader createKycReader(String correlationId, String customerId, String personId);
    KycCreator createKycCreator(String correlationId, String customerId, String personId, Kyc kyc);

    // Contact-related components
    ContactReader createContactReader(String correlationId, String customerId, String personId);
    SingleContactReader createSingleContactReader(String correlationId, String customerId, String personId, String contactId);
    ContactCreator createContactCreator(String correlationId, String customerId, String personId, Contact contact);
    ContactUpdater createContactUpdater(String correlationId, String customerId, String personId, String contactId, Contact contact);
    ContactDeleter createContactDeleter(String correlationId, String customerId, String personId, String contactId);

    // Address-related components
    AddressReader createAddressReader(String correlationId, String customerId, String personId);
    SingleAddressRead createSingleAddressReader(String correlationId, String customerId, String personId, String addressId);
    AddressCreator createAddressCreator(String correlationId, String customerId, String personId, Address address);
    AddressUpdater createAddressUpdater(String correlationId, String customerId, String personId, String addressId, Address address);
}