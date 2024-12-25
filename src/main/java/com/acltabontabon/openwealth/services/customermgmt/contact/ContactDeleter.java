package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.configs.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.OpenWealthApiProperties;
import com.acltabontabon.openwealth.dtos.ApiResponse;
import com.acltabontabon.openwealth.dtos.GenericResponse;
import com.acltabontabon.openwealth.services.CreateAsyncCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactDeleter extends CreateAsyncCommand<ApiResponse> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustomerManagement apiProperties;

    private final String customerId;
    private final String personId;
    private final String contactId;
    private final String correlationId;

    @Override
    protected ApiResponse execute() {
        try {
            restClient.delete()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .toBodilessEntity();

            return new GenericResponse<>();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete contact details", e);
        }
    }
}
