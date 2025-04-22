package com.acltabontabon.openwealth.services.customermgmt.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustomerManagement;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.services.CreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerCreator extends CreateCommand<Result<CustomerResponse>> {

    private final RestClient restClient;
    private final CustomerManagement apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final Customer customer;

    @Override
    protected Result<CustomerResponse> execute() {
        try {
            CustomerResponse response = restClient.post()
                .uri(apiProperties.getNewCustomerDetails())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CORRELATION_ID, correlationId)
                .body(customer)
                .retrieve()
                .body(CustomerResponse.class);

            return Result.success(response);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to create customer", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}
