package com.acltabontabon.openwealth.services.customermgmt.contact;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.services.DeleteCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class ContactDeleter extends DeleteCommand<Result<Void>> {

    private final RestClient restClient;
    private final ApiProperties.CustomerManagement apiProperties;

    private final String correlationId;
    private final String customerId;
    private final String personId;
    private final String contactId;

    @Override
    protected Result<Void> execute() {
        try {
            restClient.delete()
                .uri(builder -> builder.path(apiProperties.getPersonContact()).build(this.customerId, this.personId, this.contactId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .toBodilessEntity();

            return Result.success(null);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to delete contact details", e.getStatusMessage());
        }
    }
}
