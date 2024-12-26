package com.acltabontabon.openwealth.services.customermgmt.prospect;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.ProspectResponse;
import com.acltabontabon.openwealth.models.Prospect;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Conduct a pre-check to validate if the prospect might be accepted as customer by the custody
 * bank based on base parameters like domicile / nationality etc.
 */
@RequiredArgsConstructor
public class PreCheckCreator extends CreateCommand<ProspectResponse> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

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
    protected ProspectResponse execute() {
        return restClient.post()
            .uri(apiProperties.getProspectPreCheck())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HEADER_CORRELATION_ID, this.correlationId)
            .body(prospect)
            .retrieve()
            .body(ProspectResponse.class);
    }
}
