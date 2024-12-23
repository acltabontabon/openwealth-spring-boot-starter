package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.services.QueryAsyncCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycQuery extends QueryAsyncCommand<List<Kyc>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String correlationId;

    @Override
    protected List<Kyc> execute() {
        try {
            return restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person details",e);
        }
    }
}
