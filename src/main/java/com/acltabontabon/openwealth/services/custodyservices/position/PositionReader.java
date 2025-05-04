package com.acltabontabon.openwealth.services.custodyservices.position;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PositionReader {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private String correlationId;

    public PositionReader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public SinglePositionReader withPositionId(String positionId) {
        return new SinglePositionReader(restClient, apiProperties, asyncExecutor, correlationId, positionId);
    }
}
