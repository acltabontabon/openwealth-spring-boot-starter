package com.acltabontabon.openwealth.services.customermgmt.kyc;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Kyc;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class KycReader extends ReadCommand<Result<List<Kyc>>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    @Override
    protected Result<List<Kyc>> execute() {
        try {
            List<Kyc> kyc = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonKyc()).build(customerId, personId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(kyc);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch kyc details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
