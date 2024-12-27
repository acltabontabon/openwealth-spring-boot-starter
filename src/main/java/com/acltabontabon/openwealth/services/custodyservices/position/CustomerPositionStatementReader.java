package com.acltabontabon.openwealth.services.custodyservices.position;

import static com.acltabontabon.openwealth.commons.Constants.HEADER_CORRELATION_ID;

import com.acltabontabon.openwealth.commons.OperationResult;
import com.acltabontabon.openwealth.configs.ApiProperties;
import com.acltabontabon.openwealth.exceptions.FailedRequestException;
import com.acltabontabon.openwealth.models.custodyservices.CustomerPositionStatement;
import com.acltabontabon.openwealth.services.ReadCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class CustomerPositionStatementReader extends ReadCommand<OperationResult<CustomerPositionStatement>> {

    private final RestClient restClient;
    private final ApiProperties.CustodyServices apiProperties;

    private final String correlationId;
    private final String customerId;

    private final LocalDate date;
    private final boolean eodIndicator;
    private final String dateType;

    @Override
    protected OperationResult<CustomerPositionStatement> execute() {
        try {
            CustomerPositionStatement response = restClient.get()
                .uri(builder -> builder.path(apiProperties.getCustomerPositionStatement())
                    .queryParam("date", this.date.toString())
                    .queryParam("eodIndicator", this.eodIndicator)
                    .queryParam("dateType", this.dateType)
                    .build(this.customerId))
                .header(HEADER_CORRELATION_ID, this.correlationId)
                .retrieve()
                .body(CustomerPositionStatement.class);

            return OperationResult.success(response);
        } catch (FailedRequestException e) {
            return OperationResult.failure("Failed to fetch customer position statement", e.getStatusMessage());
        }
    }
}
