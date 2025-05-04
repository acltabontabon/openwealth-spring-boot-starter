package com.acltabontabon.openwealth.services.custodyservices;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.custodyservices.account.AccountReader;
import com.acltabontabon.openwealth.services.custodyservices.customer.CustomerReader;
import com.acltabontabon.openwealth.services.custodyservices.position.PositionReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustodyService {

    private final RestClient openWealthRestClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    public CustomerReader customers() {
        return new CustomerReader(openWealthRestClient, apiProperties, asyncExecutor);
    }

    public AccountReader accounts() {
        return new AccountReader(openWealthRestClient, apiProperties, asyncExecutor);
    }

    public PositionReader positions() {
        return new PositionReader(openWealthRestClient, apiProperties, asyncExecutor);
    }

}
