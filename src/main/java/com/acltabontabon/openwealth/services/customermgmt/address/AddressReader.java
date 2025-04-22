package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Address;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressReader extends ReadCommand<Result<List<Address>>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    public SingleAddressRead withAddressId(String addressId) {
        return new SingleAddressRead(restClient, apiProperties, asyncExecutor, correlationId, customerId, personId, addressId);
    }

    @Override
    protected Result<List<Address>> execute() {
        try {
            List<Address> addresses = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonAddresses()).build(customerId, personId))
                .header(HEADER_CORRELATION_ID, correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return Result.success(addresses);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch addresses", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
