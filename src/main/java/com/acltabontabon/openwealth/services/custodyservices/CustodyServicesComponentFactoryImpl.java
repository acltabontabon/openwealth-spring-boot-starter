package com.acltabontabon.openwealth.services.custodyservices;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
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
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CustodyServicesComponentFactoryImpl implements CustodyServicesComponentFactory {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    @Override
    public CustomerReader createCustomerReader() {
        return new CustomerReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public AccountReader createAccountReader() {
        return new AccountReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public PositionReader createPositionReader() {
        return new PositionReader(restClient, apiProperties, asyncExecutor);
    }

    @Override
    public SingleCustomerReader createSingleCustomerReader(String correlationId, String customerId) {
        return new SingleCustomerReader(restClient, apiProperties, asyncExecutor, correlationId, customerId);
    }

    @Override
    public SingleAccountReader createSingleAccountReader(String correlationId, String accountId) {
        return new SingleAccountReader(restClient, apiProperties, asyncExecutor, correlationId, accountId);
    }

    @Override
    public SinglePositionReader createSinglePositionReader(String correlationId, String positionId) {
        return new SinglePositionReader(restClient, apiProperties, asyncExecutor, correlationId, positionId);
    }

    @Override
    public CustomerPositionStatementReader createCustomerPositionStatementReader(String correlationId, String customerId, LocalDate date, boolean eodIndicator, DateType dateType) {
        return new CustomerPositionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, date, eodIndicator, dateType);
    }

    @Override
    public AccountPositionStatementReader createAccountPositionStatementReader(String correlationId, String accountId, LocalDate date, boolean eodIndicator, DateType dateType) {
        return new AccountPositionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, accountId, date, eodIndicator, dateType);
    }

    @Override
    public CustomerTransactionStatementReader createCustomerTransactionStatementReader(String correlationId, String customerId, LocalDate date, boolean eodIndicator, DateType dateType) {
        return new CustomerTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, date, eodIndicator, dateType);
    }

    @Override
    public AccountTransactionStatementReader createAccountTransactionStatementReader(String correlationId, String accountId, LocalDate date, boolean eodIndicator, DateType dateType) {
        return new AccountTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, accountId, date, eodIndicator, dateType);
    }

    @Override
    public PositionTransactionStatementReader createPositionTransactionStatementReader(String correlationId, String positionId, LocalDate date, boolean eodIndicator, DateType dateType) {
        return new PositionTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, positionId, date, eodIndicator, dateType);
    }
}