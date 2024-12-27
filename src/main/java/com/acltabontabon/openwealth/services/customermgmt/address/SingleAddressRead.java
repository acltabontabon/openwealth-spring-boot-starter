package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Address;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleAddressRead extends ReadCommand<Result<Address>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String addressId;

    @Override
    protected Result<Address> execute() {
        try {
            Address response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonAddress()).build(this.customerId, this.personId, this.addressId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Address.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch person address details", e.getStatusMessage());
        }
    }
}

