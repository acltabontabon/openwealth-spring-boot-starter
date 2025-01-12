package com.acltabontabon.openwealth.models.orderplacement;

import com.acltabontabon.openwealth.types.AllocationCancellationReasonCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocationCancellationReason {

    /**
     * Cancelled reason expressed as a code.
     */
    private AllocationCancellationReasonCode code;

    /**
     * Cancelled reason expressed as a proprietary description that provides more details about the
     * reason of the cancellation.
     */
    private String proprietary;

}
