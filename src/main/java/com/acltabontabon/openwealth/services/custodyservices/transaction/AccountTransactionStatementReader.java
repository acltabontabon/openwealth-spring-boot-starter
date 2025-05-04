package com.acltabontabon.openwealth.services.custodyservices.transaction;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;
import static com.acltabontabon.openwealth.commons.Constants.HEADER_LIMIT;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.TransactionStatement;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class AccountTransactionStatementReader extends ReadCommand<Result<TransactionStatement>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String accountId;

    private final LocalDate date;
    private final boolean eodIndicator;
    private final DateType dateType;

    private Integer limit;

    public AccountTransactionStatementReader withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @Override
    protected Result<TransactionStatement> execute() {
        try {
            TransactionStatement response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getAccountTransactionStatement())
                    .queryParam("date", date.toString())
                    .queryParam("eodIndicator", eodIndicator)
                    .queryParam("dateType", dateType)
                    .build(accountId))
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }

                    if (limit != null && limit > 0) {
                        headers.set(HEADER_LIMIT, String.valueOf(limit));
                    }
                })
                .retrieve()
                .body(TransactionStatement.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch account transaction statement", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
