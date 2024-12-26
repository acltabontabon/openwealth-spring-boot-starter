package com.acltabontabon.openwealth.services.customermgmt.kyc;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Kyc;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycReader extends ReadCommand<GenericResponse<List<Kyc>>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    @Override
    protected GenericResponse<List<Kyc>> execute() {
        try {
            List<Kyc> kyc = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return new GenericResponse<>(kyc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch kyc details",e);
        }
    }
}
