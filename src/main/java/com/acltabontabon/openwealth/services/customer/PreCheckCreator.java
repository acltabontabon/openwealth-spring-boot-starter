package com.acltabontabon.openwealth.services.customer;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.dtos.PreCheckApiResponse;
import com.acltabontabon.openwealth.models.Prospect;
import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Conduct a pre-check to validate if the prospect might be accepted as customer by the custody
 * bank based on base parameters like domicile / nationality etc.
 */
@RequiredArgsConstructor
public class PreCheckCreator extends CreateAsyncCommand<PreCheckApiResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private String correlationId;
    private Prospect prospect;

    public PreCheckCreator withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public PreCheckCreator prospect(Prospect prospect) {
        this.prospect = prospect;
        return this;
    }

    @Override
    protected PreCheckApiResponse execute() {
        return restClient.post()
            .uri(apiProperties.getProspectPreCheck())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .body(prospect)
            .retrieve()
            .body(PreCheckApiResponse.class);
    }
}
