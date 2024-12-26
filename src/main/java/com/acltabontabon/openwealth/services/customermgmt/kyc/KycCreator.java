package com.acltabontabon.openwealth.services.customermgmt.kyc;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.dtos.KycResponse;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycCreator extends CreateCommand<KycResponse> {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private final Kyc newKyc;

    @Override
    protected KycResponse execute() {
        try {
            return restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newKyc)
                .retrieve()
                .body(KycResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create KYC details", e);
        }
    }
}
