package com.acltabontabon.openwealth.dto;

import com.acltabontabon.openwealth.model.Customer;
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
public class CustomerResponse implements ApiResponse {

    @Singular
    private List<Customer> customers;

}
