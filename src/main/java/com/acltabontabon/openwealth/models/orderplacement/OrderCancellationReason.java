package com.acltabontabon.openwealth.models.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancellationReason {

    /**
     * Cancelled reason expressed as a code.
     * FIXME: This should be an enum.
     */
    private String code;

    /**
     * Cancelled reason expressed as a proprietary description that provides more details about the
     * reason of the cancellation.
     */
    private String proprietary;

}
