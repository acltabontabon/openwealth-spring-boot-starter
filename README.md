# OpenWealth Starter


OpenWealth Starter is a lightweight and developer-friendly Spring Boot library that simplifies integration with OpenWealth APIs.

# Features
- Fully compatible with OpenWealth API v2
- Supports both synchronous and asynchronous requests
- Provides a Fluent interface for seamless integration with OpenWealth backend services

# Usage
## Customer Management API

### Using `CustomerService`
```java
public class Example {
    
    @Autowired
    private customerService customerService;
    
    // --- Synchronous examples ---
    public void createCustomer() {
        customerService.newCustomer()
            .withExternalReference("extRef123")
            .named("Joselito Balagbag")
            .withStatus("active")
            .openedOn("2022-01-01")
            .speakingLanguage("EN")
            .inSegment("WealthManagementEMEA")
            .advisedBy("Jose Rizal")
            .withDeputyAdvisor("Juan Luna")
            .withPreviousAdvisor("Emilio Aguinaldo")
            .withRelations(relations)
            .withDocuments(documents)
            .submit();
    }
    
    public void updateCustomerContacts() {
        customerService.updateCustomerContactDetails(customerId, personId)
            .externalReference("extRef123")
            .medium("email")
            .type("work")
            .content("1234")
            .priority("Preferred phone number")
            .additionalInfo("Additional information")
            .submit();
    }
    
    public void fetchAllCustomers() {
        customerService.customers()
            .fetch();
    }
    
    public void fetchSingleCustomer() {
        customerService.customers()
            .withCustomerId("12345")
            .fetch();
    }
    
    public void fetchSingleCustomerDetails() {
        customerService.customers()
            .withCustomerId("12345")
                .fullDetails()
            .fetch();
    }
    
    // --- Asynchronous examples ---
    public void fetchAsyncAllCustomers() {
        customerService.customers()
            .fetchAsync(
                customers -> log.info("Customers: {}", customers), 
                error -> log.error("Error: {}", error)
            );
    }

    public void fetchAsyncSingleCustomer() {
        customerService.customers()
            .withCustomerId("12345")
            .fetchAsync(
                customer -> log.info("Customer: {}", customer),
                error -> log.error("Error: {}", error)
            );
    }

    public void fetchAsyncSingleCustomerDetails() {
        customerService.customers()
            .withCustomerId("12345")
                .fullDetails()
            .fetchAsync(
                customer -> log.info("Customer: {}", customer),
                error -> log.error("Error: {}", error)
            );
    }
}
```

### Using `ProspectService`

```java
public class Example {

    @Autowired
    private ProspectService prospectService;

    public void conductPreCheck() {
        prospectService.preCheck()
            .withCorrelationId(correlationId)
            .prospect(Prospect.builder()
                .firstName("Anthony")
                .lastName("Stark")
                .nationality("CH")
                .domicile("CH")
                .birthdate(LocalDate.of(2018, 4, 13))
                .build())
            .submit();
    }

    public void fetchProspectStatus() {
        prospectService.prospectStatus()
            .withTemporaryId("tempId123")
            .fetch();
    }

    public void fetchAsyncProspectStatus() {
        prospectService.prospectStatus()
            .withTemporaryId("tempId123")
            .fetchAsync(
                status -> log.info("Status: {}", status),
                error -> log.error("Error: {}", error)
            );
    }
}
```


# Motivation
Created this library for fun and learning. If you somehow find this helpful and/or useful, I'd be grateful for a cup of coffee. :grin: :coffee:

<a href='https://ko-fi.com/acltabontabon' target='_blank'><img style='height:30px;' src='https://az743702.vo.msecnd.net/cdn/kofi3.png?v=1' border='0' alt='Buy Me a Coffee at ko-fi.com'></a>
