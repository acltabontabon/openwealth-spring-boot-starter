package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressReader extends ReadCommand<GenericResponse<List<Address>>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;

    public SingleAddressRead withAddressId(String addressId) {
        return new SingleAddressRead(restClient, apiProperties, correlationId, customerId, personId, addressId);
    }

    @Override
    protected GenericResponse<List<Address>> execute() {
        try {
            List<Address> addresses = restClient.get()
                .uri(builder -> builder.path(apiProperties.getPersonAddresses()).build(this.customerId, this.personId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return new GenericResponse<>(addresses);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch addresses",e);
        }
    }
}
