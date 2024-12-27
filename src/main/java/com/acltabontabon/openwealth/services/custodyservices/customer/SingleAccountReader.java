package com.acltabontabon.openwealth.services.custodyservices.customer;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.position.AccountPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.position.CustomerPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transaction.AccountTransactionStatementReader;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleAccountReader {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String accountId;

    public AccountPositionStatementReader positionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new AccountPositionStatementReader(restClient, apiProperties, correlationId, accountId, date, eodIndicator, dateType);
    }

    public AccountTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new AccountTransactionStatementReader(restClient, apiProperties, correlationId, accountId, date, eodIndicator, dateType);
    }
}

