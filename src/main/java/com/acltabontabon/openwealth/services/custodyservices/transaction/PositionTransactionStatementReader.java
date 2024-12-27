package com.acltabontabon.openwealth.services.custodyservices.transaction;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.TransactionStatement;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class PositionTransactionStatementReader extends ReadCommand<OperationResult<TransactionStatement>> {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String accountId;

    private final LocalDate date;
    private final boolean eodIndicator;
    private final String dateType;

    @Override
    protected OperationResult<TransactionStatement> execute() {
        try {
            TransactionStatement response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPositionTransactionStatement())
                    .queryParam("date", this.date.toString())
                    .queryParam("eodIndicator", this.eodIndicator)
                    .queryParam("dateType", this.dateType)
                    .build(this.accountId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(TransactionStatement.class);

            return OperationResult.success(response);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch account transaction statement", e.getStatusMessage());
        }
    }
}
