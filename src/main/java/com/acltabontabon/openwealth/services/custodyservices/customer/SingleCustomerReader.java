package com.acltabontabon.openwealth.services.custodyservices.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.custodyservices.position.CustomerPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transaction.CustomerTransactionStatementReader;
import com.acltabontabon.openwealth.types.DateType;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerReader extends ReadCommand<Result<Customer>> {

    private final RestClient restClient;
    private final OpenWealthApiProperties.CustodyServices apiProperties;
    private final TaskExecutor asyncExecutor;

    private final String correlationId;
    private final String customerId;

    public CustomerPositionStatementReader positionStatement(LocalDate date, boolean eodIndicator, DateType dateType) {
        return new CustomerPositionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, date, eodIndicator, dateType);
    }

    public CustomerTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new CustomerTransactionStatementReader(restClient, apiProperties, asyncExecutor, correlationId, customerId, date, eodIndicator, dateType);
    }

    @Override
    protected Result<Customer> execute() {
        try {
            Customer customer = restClient.get()
                .uri(builder -> builder.path(apiProperties.getCustomer()).build(customerId))
                .headers(headers -> {
                    if (correlationId != null) {
                        headers.set(HEADER_CORRELATION_ID, correlationId);
                    }
                })
                .retrieve()
                .body(Customer.class);

            return Result.success(customer);
        } catch (FailedRequestException e) {
            return Result.failure("Failed to fetch customer details", e.getStatusMessage());
        }
    }

    @Override
    protected TaskExecutor asyncExecutor() {
        return this.asyncExecutor;
    }
}

