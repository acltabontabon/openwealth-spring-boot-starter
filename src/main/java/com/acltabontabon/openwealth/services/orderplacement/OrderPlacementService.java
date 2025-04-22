package com.acltabontabon.openwealth.services.orderplacement;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.orderplacement.access.AccountAccessReader;
import com.acltabontabon.openwealth.services.orderplacement.status.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class OrderPlacementService {

    private final RestClient openWealthRestClient;
    private final OpenWealthApiProperties.OrderPlacement apiProperties;
    private final TaskExecutor asyncExecutor;

    public OrderReader orders() {
        return new OrderReader(openWealthRestClient, apiProperties, asyncExecutor);
    }

    public AccountAccessReader accountAccesses() {
        return new AccountAccessReader(openWealthRestClient, apiProperties, asyncExecutor);
    }
}
