package com.acltabontabon.openwealth.service.prospect;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ProspectService {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    public PreCheckRequest preCheck() {
        return new PreCheckRequest(restClient, apiProperties);
    }

    public PreCheckStatusRequest preCheckStatus() {
        return new PreCheckStatusRequest(restClient, apiProperties);
    }
}
