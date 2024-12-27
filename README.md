
# OpenWealth Spring Starter

![version](https://img.shields.io/badge/version-1.0.0--Alpha.3-blue)
![java](https://img.shields.io/badge/java-17%2B-blue)
![spring-boot](https://img.shields.io/badge/spring--boot-3.3.x-blue)
![license](https://img.shields.io/github/license/acltabontabon/openwealth-spring-starter)
[![CodeQL](https://github.com/acltabontabon/openwealth-spring-starter/actions/workflows/codeql.yml/badge.svg)](https://github.com/acltabontabon/openwealth-spring-starter/actions/workflows/codeql.yml)

**A lightweight and developer-friendly Spring Boot library that simplifies integration with [OpenWealth API](https://openwealth.ch).**

> **Disclaimer**  
> This project is not affiliated with or endorsed by OpenWealth or Synpulse. It is an independent effort to provide a convenient library for developers working with OpenWealth APIs.  
>
> For more information about OpenWealth, visit their [official website](https://openwealth.ch).


## Features

- ✅ Compatible with OpenWealth APIs:
  - 🚀 **Order Placement API** `v2.0.8`
  - 👥 **Customer Management API** `v2.0.6`
  - 🔒 **Custody Services API** `v2.0.3`
- ✨ Simplifies integration with OpenWealth backend services using a Fluent API design
- 🔄 Supports both synchronous and asynchronous operations

## Usage

### Maven
```xml
<dependency>
    <groupId>com.acltabontabon</groupId>
    <artifactId>openwealth-spring-starter</artifactId>
    <version>1.0.0-Alpha.3</version>
</dependency>
```

### Gradle
```gradle
implementation 'com.acltabontabon:openwealth-spring-starter:1.0.0-Alpha.3'
```

### Example
Here's a simple usage example to get you started:
```java
@Slf4j
@Component
public class Example {

    @Autowired
    private CustomerService customerService;

    public void printCustomers() {
        OperationResult<List<Customer>> result = customerService.customers().fetch();

        log.info("List of customers: {}", result.getData());
    }
}
```

For full documentation and examples: [Documentation (TBA)](https://acltabontabon.com/openwealth-spring-starter/)

## Support

Love this project? Support its development by [buying me a coffee](https://ko-fi.com/acltabontabon)! ☕

<span><a href='https://ko-fi.com/acltabontabon' target='_blank'><img style='height:30px;' src='https://az743702.vo.msecnd.net/cdn/kofi3.png?v=1' border='0' alt='Buy Me a Coffee at ko-fi.com'></a></span>


## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
