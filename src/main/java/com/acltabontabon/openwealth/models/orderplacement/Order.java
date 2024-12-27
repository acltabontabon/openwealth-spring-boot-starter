package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.models.custodyservices.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Date and time of the statement creation (UTC Timestamp). In accordance with ISO 8601.
     */
    private String statementDateTime;

    /**
     * Order object with extended allocation information.
     */
    private ExtendedOrder extendedOrder;

    private OrderState orderState;

    /**
     * Indicates dates relevant for the respective order.
     */
    @Singular("addDate")
    private List<Date> dateList;
}
