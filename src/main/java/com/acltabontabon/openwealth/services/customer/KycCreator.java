package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.CustomerApiResponse;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycCreator extends CreateAsyncCommand<CustomerApiResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String correlationId;

    private final Kyc kycToAdd;

    @Override
    protected CustomerApiResponse execute() {
        try {
            return restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(kycToAdd)
                .retrieve()
                .body(CustomerApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create KYC details", e);
        }
    }
}
