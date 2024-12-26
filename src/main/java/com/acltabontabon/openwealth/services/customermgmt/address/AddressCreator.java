package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.AddressResponse;
import com.acltabontabon.openwealth.dtos.ApiResponse;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressCreator extends CreateCommand<ApiResponse> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    private final Address newAddress;

    @Override
    protected ApiResponse execute() {
        try {
            return restClient.post()
                .uri(builder -> builder.path(apiProperties.getPersonAddresses()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(newAddress)
                .retrieve()
                .body(AddressResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create address details", e);
        }
    }
}
