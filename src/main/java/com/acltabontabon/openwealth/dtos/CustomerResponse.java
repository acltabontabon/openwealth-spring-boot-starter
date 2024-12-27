package com.acltabontabon.openwealth.dtos;

import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class CustomerResponse {

    private String temporaryId;

    private String externalReference;

    // TODO: revisit this as there seems to be an inconsistency in the OpenWealth API
    private List<Customer> customers;
}
