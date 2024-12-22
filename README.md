# Overview

OpenWealth Starter is a lightweight and developer-friendly Spring Boot library that simplifies 
integration with [OpenWealth API](https://openwealth.ch).

>**Disclaimer**: This project is not affiliated with or endorsed by OpenWealth or Synpulse. It is an 
> independent effort to provide a convenient library for developers working with OpenWealth APIs.
>
> For more information about OpenWealth, visit their [official website](https://openwealth.ch).

> 🚧 **Under Construction!** 🚧
>
> Heads up! This project is a living work-in-progress 🛠️. Things might break, move, or spontaneously transform.

# Features

- Fully compatible with OpenWealth API v2
  - Customer Management API v2.0.6
- Supports both synchronous and asynchronous requests
- Provides a Fluent API for seamless integration with OpenWealth backend services

# Motivation
Created this library for fun and learning. If you somehow find this helpful and/or useful, I'd be 
grateful for a cup of coffee. :grin: :coffee:

<a href='https://ko-fi.com/acltabontabon' target='_blank'><img style='height:30px;' src='https://az743702.vo.msecnd.net/cdn/kofi3.png?v=1' border='0' alt='Buy Me a Coffee at ko-fi.com'></a>

# Usage

## Configuration Properties

The following table lists the configuration properties for the OpenWealth API, including their 
descriptions and default values.

| **Property**                                                   | **Description**                                                                               | **Default Value**                                                            |
|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| `openwealth.api.base-url`                                      | Base URL for all OpenWealth API requests                                                      | `https://api.openwealth.synpulse8.com/api`                                   |
| `openwealth.api.access-token`                                  | Access token used to authenticate API requests                                                |                                                                              |
| **CUSTOMER MANAGEMENT**                                        |                                                                                               |                                                                              |
| `openwealth.api.customer-management`                           | Base path for the Customer Management API                                                     | `/customer-management/v2`                                                    |
| `openwealth.api.customer-management.customers`                 | Resource path to retrieve a list of customers                                                 | `${openwealth.api.customer-management}/customers`                            |
| `openwealth.api.customer-management.customer`                  | Resource path to retrieve details of a specific customer                                      | `${openwealth.api.customer-management.customers}/{customerId}`               |
| `openwealth.api.customer-management.customer-details`          | Resource path to retrieve the complete details of a specific customer                         | `${openwealth.api.customer-management.customer}/customer-details`            |
| `openwealth.api.customer-management.create-customer-details`   | Resource path to create new customer details                                                  | `${openwealth.api.customer-management}/customer-details`                     |
| `openwealth.api.customer-management.persons`                   | Resource path to retrieve a list of associated persons for a customer                         | `${openwealth.api.customer-management.customer}/persons`                     |
| `openwealth.api.customer-management.person`                    | Resource path to retrieve details of a specific associated person for a customer              | `${openwealth.api.customer-management.persons}/{personId}`                   |
| `openwealth.api.customer-management.person-details`            | Resource path to retrieve the complete details of a specific associated person for a customer | `${openwealth.api.customer-management.customer}/person-details/{personId}`   |
| `openwealth.api.customer-management.create-person-details`     | Resource path to create and associate person details with a customer                          | `${openwealth.api.customer-management.customer}/person-details`              |
| `openwealth.api.customer-management.prospect-pre-check`        | Resource path to perform a prospect pre-check                                                 | `${openwealth.api.customer-management}/prospect-precheck`                    |
| `openwealth.api.customer-management.prospect-pre-check-status` | Resource path to retrieve the status of a prospect pre-check                                  | `${openwealth.api.customer-management.prospect-pre-check}/{temporaryId}`     |

## Supported Operations

- Customer Service
    - Create a new customer
    - Retrieve customer details
    - Update customer details
- Prospect Service
    - Conduct pre-check
    - Check prospect status

## Examples

### Using `CustomerService`
```java
@Autowrired
private CustomerService customerService;
```

#### Get customer information
```java
public void fetchAllCustomers() {
    // all customers
    customerService.customers()
        .fetch();
    
    // single customer basic details
    customerService.customers()
        .withCustomerId("customerId")
        .fetch();
    
    // single customer with complete details
    customerService.customers()
        .withCustomerId("customerId")
        .completeDetails()
        .fetch();
    
    // asynchronous request
    customerService.customers()
        .fetchAsync(
            customers -> log.info("List of customers: {}", customers.getCustomers()),
            error -> log.error("Error: {}", error)
        );
}
```

#### Retrieve persons associated with a specific customer
```java
public void fetchAssociatedPersons() {
    // all associated persons
    customerService.customers()
        .withCustomerId("customerId")
        .associatedPersons()
        .fetch();
    
    // single associated person basic details
    customerService.customers()
        .withCustomerId("customerId")
        .associatedPersons()
        .withPersonId("personId")
        .completeDetails()
        .fetch();
    
    // single associated person with complete details
    customerService.customers()
        .withCustomerId("customerId")
        .associatedPersons()
        .withPersonId("personId")
        .fetch();
}
```

#### Create a new customer
```java
public void createCustomer() {
    // synchronous
    customerService.customers()
        .createNew(c)
        .submit();

    // asynchronous request
    customerService.customers()
        .createNew(c)
        .submitAsync(
            response -> log.info("CustomerOperationResponse: {}", response),
            error -> log.error("Error: {}", error)
        );
}
```

#### Update customer information
```java
public void updateCustomer() {
    // add a person object to an existing customer
    customerService.customers()
        .withCustomerId(customerId)
        .addPerson(person)
        .submit();

    // asynchronous request
    customerService.customers()
        .withCustomerId(customerId)
        .addPerson(person)
        .submitAsync(
            response -> log.info("CustomerOperationResponse: {}", customer),
            error -> log.error("Error: {}", error)
        );
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
