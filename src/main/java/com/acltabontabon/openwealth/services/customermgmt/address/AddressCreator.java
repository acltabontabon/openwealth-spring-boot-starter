package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.dtos.AddressResponse;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressCreator extends CreateCommand<OperationResult<AddressResponse>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private final Address newAddress;

    @Override
    protected OperationResult<AddressResponse> execute() {
        try {
            AddressResponse response = restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonAddresses()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newAddress)
                .retrieve()
                .body(AddressResponse.class);

            return OperationResult.success(response);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to create address details", e.getStatusMessage());
        }
    }
}
