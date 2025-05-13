# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
### Changed
### Fixed
### Removed


## [1.0.0-Alpha.6] - 2025-05-04
### Added
- Custody Service
  - Add support for limiting the number of position statements returned for both accounts and customers
  - Add support for limiting the number of transaction statements returned
- Add `CorrelationIdRequestInterceptor` for handling correlation IDs in outgoing HTTP requests
- Add `MDC_CORRELATION_ID` constant for consistent MDC key usage
- Order Placement Service
  - Add `@JsonInclude(JsonInclude.Include.NON_NULL)` annotation to model classes:
    - `PlaceOfTrade`
    - `BulkOrderDetails`
    - `RequestedOrder`
  - Add support for limiting the number of orders returned in the response
### Changed
- Upgrade `org.springframework.boot` plugin version from `3.4.4` to `3.4.5`
- Upgrade `org.junit:junit-bom` version from `5.12.1` to `5.12.2`
- Upgrade `org.mockito:mockito-core` version from `5.16.1` to `5.17.0`
- Upgrade `org.mockito:mockito-junit-jupiter` version from `5.16.1` to `5.17.0`
- Refactor correlation ID handling in HTTP requests
- Change `OrderSide` enum values from uppercase ("BUY", "SELL") to lowercase ("buy", "sell")
- Order Placement Service
  - Improve header handling in REST client requests
  - Rename `cancelOrder` method to `cancel` in SingleOrderReader for more concise API
- Custody Service
  - Refactored package structure to improve organization and make syntax more intuitive:
    - Moved position-related classes from `customer` package to `position` package
    - Moved account-related classes from `customer` package to `account` package
    - Renamed `transaction` package to `transactionstatement` for clarity
    - Created `positionstatement` package for position statement related classes
  - Account: change `dateType` parameter in `positionStatement` method from `String` to `DateType` enum
  - Account: change `dateType` parameter in `transactionStatement` method from `String` to `DateType` enum
  - Customer: change `dateType` parameter in `transactionStatement` method from `String` to `DateType` enum
  - AccountDetails: change `accountIdentificationType` field type from `AccountIdentificationType` to `AccountType`

## [1.0.0-Alpha.5] - 2025-05-04
### Added
- Custody Service
  - Add support for limiting the number of customers returned via a new header parameter
### Changed
- Change project name from `openwealth-spring-starter` to `openwealth-spring-boot-starter`
- Custody Service
  - Improve error handling in `CustomerReader` to use status codes instead of status messages
  - Change `amountType` field in `FinancialInstrumentPrice` from `PriceType` to `PriceRepresentation`
  - Change `dateType` parameter in `positionStatement` method from `String` to `DateType` enum
### Fixed
- Fix incorrect assignment of auto-generated correlation ID in RestClient headers

## [1.0.0-Alpha.4] - 2025-04-23
### Added
- Add support for custom token resolution via `TokenProvider` interface
- Enables users to customize the executor for asynchronous operations
### Changed
- Upgrade `org.springframework.boot` plugin version from `3.3.5` to `3.4.4`
- Upgrade `io.spring.dependency-management` plugin version from `1.1.6` to `1.1.7`
- Upgrade `org.junit:junit-bom` version from `5.10.0` to `5.12.1`
- Upgrade `org.mockito:mockito-core` version from `5.5.0` to `5.16.1`
- Upgrade `org.mockito:mockito-junit-jupiter` version from `5.5.0` to `5.16.1`
- Upgrade `Java` version from `17` to `21`
- Replace references to `ApiProperties` with `OpenWealthApiProperties`
