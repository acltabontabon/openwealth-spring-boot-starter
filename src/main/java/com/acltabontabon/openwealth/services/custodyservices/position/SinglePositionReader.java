package com.acltabontabon.openwealth.services.custodyservices.position;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.transactionstatement.PositionTransactionStatementReader;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SinglePositionReader {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String positionId;

    public PositionTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, DateType dateType) {
        return new PositionTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, positionId, date, eodIndicator, dateType);
    }
}

