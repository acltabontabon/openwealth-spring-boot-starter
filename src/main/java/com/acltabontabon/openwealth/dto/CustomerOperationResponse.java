package com.acltabontabon.openwealth.dto;

import com.acltabontabon.openwealth.models.Customer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOperationResponse implements ApiResponse {

    private String temporaryId;

    private String externalReference;

    @Singular
    private List<Customer> customers;

}
