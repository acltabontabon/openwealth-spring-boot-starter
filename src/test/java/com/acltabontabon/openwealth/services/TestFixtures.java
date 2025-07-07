package com.acltabontabon.openwealth.services;

import com.acltabontabon.openwealth.models.custodyservices.FinancialInstrumentIdentification;
import com.acltabontabon.openwealth.models.custodyservices.PlaceOfTrade;
import com.acltabontabon.openwealth.models.orderplacement.Account;
import com.acltabontabon.openwealth.models.orderplacement.BulkOrderDetails;
import com.acltabontabon.openwealth.models.orderplacement.FinancialInstrumentDetails;
import com.acltabontabon.openwealth.models.orderplacement.OrderQuantity;
import com.acltabontabon.openwealth.models.orderplacement.RequestedAllocation;
import com.acltabontabon.openwealth.models.orderplacement.RequestedOrder;
import com.acltabontabon.openwealth.types.AccountType;
import com.acltabontabon.openwealth.types.AmountType;
import com.acltabontabon.openwealth.types.ExecutionType;
import com.acltabontabon.openwealth.types.OrderSide;
import com.acltabontabon.openwealth.types.SecurityIdentifierType;
import com.acltabontabon.openwealth.types.TimeInForce;

public final class TestFixtures {

    public static final String MOCK_URL = "http://mock-api/customers";
    public static final String TEST_CORRELATION_ID = "test-correlation-id";
    public static final int TEST_LIMIT = 10;

    public static RequestedOrder createTestRequestedOrder() {
        return RequestedOrder.builder()
            .clientOrderIdentification("XR002")
            .bulkOrderDetails(BulkOrderDetails.builder()
                .side(OrderSide.BUY)
                .orderQuantity(OrderQuantity.builder()
                    .amount("600")
                    .type(AmountType.UNITS_NUMBER)
                    .build())
                .numberOfAllocations(2)
                .financialInstrumentDetails(FinancialInstrumentDetails.builder()
                    .financialInstrumentIdentification(
                        FinancialInstrumentIdentification.builder()
                            .identification("US88160R1014")
                            .type(SecurityIdentifierType.ISIN)
                            .build())
                    .build())
                .placeOfTrade(PlaceOfTrade.builder()
                    .marketIdentificationCode("XNAS")
                    .build())
                .currency("USD")
                .executionType(ExecutionType.LIMIT)
                .limitPrice("140")
                .timeInForce(TimeInForce.GOOD_TILL_DATE)
                .expiryDateTime("2023-05-13T11:11:11Z")
                .build())
            .addRequestedAllocation(RequestedAllocation.builder()
                .addAccount(Account.builder()
                    .identification("account_001")
                    .type(AccountType.SAFEKEEPING_ACCOUNT)
                    .build())
                .addAccount(Account.builder()
                    .identification("account_006")
                    .type(AccountType.CASH_ACCOUNT)
                    .build())
                .clientAllocationIdentification("001")
                .amount("300")
                .build())
            .addRequestedAllocation(RequestedAllocation.builder()
                .addAccount(Account.builder()
                    .identification("account_007")
                    .type(AccountType.SAFEKEEPING_ACCOUNT)
                    .build())
                .addAccount(Account.builder()
                    .identification("account_006")
                    .type(AccountType.CASH_ACCOUNT)
                    .build())
                .clientAllocationIdentification("002")
                .amount("300")
                .build())
            .build();
    }

}
