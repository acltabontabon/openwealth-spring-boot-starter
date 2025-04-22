package com.acltabontabon.openwealth.services.custodyservices.position;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.AccountPositionStatement;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class AccountPositionStatementReader extends ReadCommand<Result<AccountPositionStatement>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;

    private final LocalDate date;
    private final boolean eodIndicator;
    private final String dateType;

    @Override
    protected Result<AccountPositionStatement> execute() {
        try {
            AccountPositionStatement response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getAccountPositionStatement())
                    .queryParam("date", date.toString())
                    .queryParam("eodIndicator", eodIndicator)
                    .queryParam("dateType", dateType)
                    .build(customerId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .retrieve()
                .body(AccountPositionStatement.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch account position statement", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
