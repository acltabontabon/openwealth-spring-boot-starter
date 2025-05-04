package com.acltabontabon.openwealth.services.custodyservices.customer;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.position.AccountPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transaction.AccountTransactionStatementReader;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleAccountReader {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String accountId;

    public AccountPositionStatementReader positionStatement(LocalDate date, boolean eodIndicator, DateType dateType) {
        return new AccountPositionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, accountId, date, eodIndicator, dateType);
    }

    public AccountTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, DateType dateType) {
        return new AccountTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, accountId, date, eodIndicator, dateType);
    }
}

