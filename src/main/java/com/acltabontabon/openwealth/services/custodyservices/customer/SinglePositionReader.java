package com.acltabontabon.openwealth.services.custodyservices.customer;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.transaction.AccountTransactionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transaction.PositionTransactionStatementReader;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePositionReader {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String positionId;

    public PositionTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new PositionTransactionStatementReader(restClient, apiProperties, correlationId, positionId, date, eodIndicator, dateType);
    }
}

