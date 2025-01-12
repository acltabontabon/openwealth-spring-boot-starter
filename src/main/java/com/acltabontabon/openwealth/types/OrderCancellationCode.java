package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderCancellationCode {

    ALLOCATION_INVALID("allocationInvalid"),
    CANCELLED_BY_HUB("cancelledByHub"),
    CANCELLED_BY_INSTRUCTING_PARTY("cancelledByInstructingParty"),
    CANCELLED_BY_OTHER("cancelledByOther"),
    CANNOT_CONTAIN_MARKET_IDENTIFICATION_AND_MARKET_DESCRIPTION("cannotContainMarketIdentificationAndMarketDescription"),
    CURRENCY_NOT_SUPPORTED_FOR_FINANCIAL_INSTRUMENT("currencyNotSupportedForFinancialInstrument"),
    DISPLAY_QUANTITY_NOT_IMPLEMENTED("displayQuantityNotImplemented"),
    END_OF_LIFE("endOfLife"),
    EXECUTION_TYPE_INCONSEQUENTIAL("executionTypeInconsequential"),
    EXECUTION_TYPE_NOT_SUPPORTED("executionTypeNotSupported"),
    EXPIRY_DATE_TIME_IN_THE_PAST("expiryDateTimeInThePast"),
    EXPIRY_DATE_TIME_MISSING("expiryDateTimeMissing"),
    EXPIRY_DATE_TIME_NOT_ALLOWED("expiryDateTimeNotAllowed"),
    FINANCIAL_INSTRUMENT_IDENTIFICATION_NOT_UNIQUE("financialInstrumentIdentificationNotUnique"),
    FINANCIAL_INSTRUMENT_IDENTIFICATION_TYPE_NOT_SUPPORTED("financialInstrumentIdentificationTypeNotSupported"),
    FINANCIAL_INSTRUMENT_NOT_ALLOWED_VIA_INTERFACE("financialInstrumentNotAllowedViaInterface"),
    FINANCIAL_INSTRUMENT_NOT_FOUND("financialInstrumentNotFound"),
    FINANCIAL_INSTRUMENT_NOT_TRADED_ON_MARKET("financialInstrumentNotTradedOnMarket"),
    ICEBERG_ORDER_TOO_LARGE("icebergOrderTooLarge"),
    INVALID_PRICES("invalidPrices"),
    LIMIT_PRICE_MISSING_FOR_EXECUTION_TYPE("limitPriceMissingForExecutionType"),
    LIMIT_PRICE_NOT_ALLOWED_FOR_EXECUTION_TYPE("limitPriceNotAllowedForExecutionType"),
    MARKET_CURRENTLY_BLOCKED_FOR_TRADING("marketCurrentlyBlockedForTrading"),
    MARKET_QUOTE_NOT_AVAILABLE_FOR_PRODUCT("marketQuoteNotAvailableForProduct"),
    MAXIMUM_NUMBER_OF_ALLOCATIONS_EXCEEDED("maximumNumberOfAllocationsExceeded"),
    NUMBER_OF_ALLOCATIONS_DEVIATE_FROM_ALLOCATIONS_PROVIDED("numberOfAllocationsDeviateFromAllocationsProvided"),
    NUMBER_OF_ALLOCATIONS_EMPTY("numberOfAllocationsEmpty"),
    NUMBER_OF_ALLOCATIONS_ZERO("numberOfAllocationsZero"),
    ORDER_QUANTITY_DEVIATES_FROM_SUM_OF_ALLOCATION_QUANTITIES("orderQuantityDeviatesFromSumOfAllocationQuantities"),
    ORDER_QUANTITY_TOO_HIGH("orderQuantityTooHigh"),
    ORDER_QUANTITY_TOO_SMALL("orderQuantityTooSmall"),
    ORDER_QUANTITY_TYPE_NOT_IMPLEMENTED("orderQuantityTypeNotImplemented"),
    ORDER_QUANTITY_TYPE_WRONG("orderQuantityTypeWrong"),
    ORDER_TYPE_NOT_SUPPORTED_BY_MARKET("orderTypeNotSupportedByMarket"),
    PLACE_OF_TRADE_NOT_IMPLEMENTED("placeOfTradeNotImplemented"),
    PLACE_OF_TRADE_NOT_SUPPORTED("placeOfTradeNotSupported"),
    PRODUCT_EXPIRED("productExpired"),
    SELECTED_TIME_IN_FORCE_NOT_IMPLEMENTED("selectedTimeInForceNotImplemented"),
    STOP_PRICE_MISSING_FOR_EXECUTION_TYPE("stopPriceMissingForExecutionType"),
    STOP_PRICE_NOT_ALLOWED_FOR_EXECUTION_TYPE("stopPriceNotAllowedForExecutionType"),
    TIME_IN_FORCE_AND_EXPIRY_DATE_INCONSISTENT("timeInForceAndExpiryDateInconsistent"),
    VALUE_TOO_HIGH("valueTooHigh"),
    WRONG_DENOMINATION("wrongDenomination"),
    OTHER("other");

    private final String value;

    OrderCancellationCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static OrderCancellationCode forValue(String value) {
        for (OrderCancellationCode orderCancellationCode : OrderCancellationCode.values()) {
            if (orderCancellationCode.value.equals(value)) {
                return orderCancellationCode;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }

}
