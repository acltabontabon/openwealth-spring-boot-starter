package com.acltabontabon.openwealth.services.customermgmt.address;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties.CustomerManagementResourcePaths;
import com.acltabontabon.openwealth.models.Address;
import com.acltabontabon.openwealth.services.UpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AddressUpdater extends UpdateCommand {

    private final RestClient restClient;
    private final CustomerManagementResourcePaths apiProperties;

    private final String customerId;
    private final String personId;
    private final String addressId;
    private final String correlationId;

    private final Address updatedAddress;

    @Override
    protected Void execute() {
        try {
            restClient.put()
                .uri(builder -> builder.path(apiProperties.getPersonAddress()).build(this.customerId, this.personId, this.addressId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .body(updatedAddress)
                .retrieve()
                .toBodilessEntity();

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address details", e);
        }
    }
}
