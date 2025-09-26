package com.acltabontabon.openwealth.services.custodyservices;

import com.acltabontabon.openwealth.services.custodyservices.account.AccountReader;
import com.acltabontabon.openwealth.services.custodyservices.account.SingleAccountReader;
import com.acltabontabon.openwealth.services.custodyservices.customer.CustomerReader;
import com.acltabontabon.openwealth.services.custodyservices.customer.SingleCustomerReader;
import com.acltabontabon.openwealth.services.custodyservices.position.PositionReader;
import com.acltabontabon.openwealth.services.custodyservices.position.SinglePositionReader;
import com.acltabontabon.openwealth.services.custodyservices.positionstatement.AccountPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.positionstatement.CustomerPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transactionstatement.AccountTransactionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transactionstatement.CustomerTransactionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transactionstatement.PositionTransactionStatementReader;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;

public interface CustodyServicesComponentFactory {

    CustomerReader createCustomerReader();
    AccountReader createAccountReader();
    PositionReader createPositionReader();

    // Customer-related components
    SingleCustomerReader createSingleCustomerReader(String correlationId, String customerId);

    // Account-related components
    SingleAccountReader createSingleAccountReader(String correlationId, String accountId);

    // Position-related components
    SinglePositionReader createSinglePositionReader(String correlationId, String positionId);

    // Position statement components
    CustomerPositionStatementReader createCustomerPositionStatementReader(
        String correlationId, String customerId, LocalDate date, boolean eodIndicator, DateType dateType);
    AccountPositionStatementReader createAccountPositionStatementReader(
        String correlationId, String accountId, LocalDate date, boolean eodIndicator, DateType dateType);

    // Transaction statement components
    CustomerTransactionStatementReader createCustomerTransactionStatementReader(
        String correlationId, String customerId, LocalDate date, boolean eodIndicator, DateType dateType);
    AccountTransactionStatementReader createAccountTransactionStatementReader(
        String correlationId, String accountId, LocalDate date, boolean eodIndicator, DateType dateType);
    PositionTransactionStatementReader createPositionTransactionStatementReader(
        String correlationId, String positionId, LocalDate date, boolean eodIndicator, DateType dateType);
}