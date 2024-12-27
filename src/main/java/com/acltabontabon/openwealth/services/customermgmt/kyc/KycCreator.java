package com.acltabontabon.openwealth.services.customermgmt.kyc;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.dtos.KycResponse;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Kyc;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycCreator extends CreateCommand<OperationResult<KycResponse>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private final Kyc newKyc;

    @Override
    protected OperationResult<KycResponse> execute() {
        try {
            KycResponse response = restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newKyc)
                .retrieve()
                .body(KycResponse.class);

            return OperationResult.success(response);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to add kyc details", e.getStatusMessage());
        }
    }
}
