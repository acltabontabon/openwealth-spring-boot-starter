package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Address;
import com.acltabontabon.openwealth.services.UpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressUpdater extends UpdateCommand<OperationResult<Void>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String addressId;
    private final String correlationId;

    private final Address updatedAddress;

    @Override
    protected OperationResult<Void> execute() {
        try {
            restClient.put()
                .uri(builder -> builder.path(apiProperties.getPersonAddress()).build(this.customerId, this.personId, this.addressId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(updatedAddress)
                .retrieve()
                .toBodilessEntity();

            return OperationResult.success(null);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to update address details", e.getStatusMessage());
        }
    }
}
