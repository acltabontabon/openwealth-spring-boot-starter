package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.AmountType;
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
     */
    private AmountType type;

}
