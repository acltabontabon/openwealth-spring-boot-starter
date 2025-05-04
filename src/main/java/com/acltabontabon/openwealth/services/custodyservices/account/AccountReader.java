package com.acltabontabon.openwealth.services.custodyservices.account;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AccountReader {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private String correlationId;

    public AccountReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SingleAccountReader withAccountId(String accountId) {
        return new SingleAccountReader(restClient, apiProperties, asyncExecutor, correlationId, accountId);
    }
}
