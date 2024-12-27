package com.acltabontabon.openwealth.services.custodyservices.customer;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.services.ReadCommand;
import com.acltabontabon.openwealth.services.custodyservices.position.CustomerPositionStatementReader;
import com.acltabontabon.openwealth.services.custodyservices.transaction.CustomerTransactionStatementReader;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class SingleCustomerReader extends ReadCommand<OperationResult<Customer>> {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String customerId;

    public CustomerPositionStatementReader positionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new CustomerPositionStatementReader(restClient, apiProperties, correlationId, customerId, date, eodIndicator, dateType);
    }

    public CustomerTransactionStatementReader transactionStatement(LocalDate date, boolean eodIndicator, String dateType) {
        return new CustomerTransactionStatementReader(restClient, apiProperties, correlationId, customerId, date, eodIndicator, dateType);
    }

    @Override
    protected OperationResult<Customer> execute() {
        try {
            Customer customer = restClient.get()
                .uri(builder -> builder.path(apiProperties.getCustomer()).build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(Customer.class);

            return OperationResult.success(customer);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch customer details", e.getStatusMessage());
        }
    }
}

