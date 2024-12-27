package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQuantity {

    private String amount;

    /**
     * Type of the amount.
     * FIXME: This should be an enum.
     */
    private String type;

}
