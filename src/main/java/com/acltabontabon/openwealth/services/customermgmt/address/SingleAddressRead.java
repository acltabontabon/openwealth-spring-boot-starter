package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.services.ReadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleAddressRead extends ReadCommand<GenericResponse<Address>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String addressId;

    @Override
    protected GenericResponse<Address> execute() {
        try {
            Address contact = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonAddress()).build(this.customerId, this.personId, this.addressId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Address.class);

            return new GenericResponse<>(contact);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch person address details",e);
        }
    }
}

