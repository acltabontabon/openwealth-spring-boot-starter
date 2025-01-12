package com.acltabontabon.openwealth.models.customermgmt;

import com.acltabontabon.openwealth.types.CurrentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatusResponse {

    /**
     * Some kind of ID (Customer/Person/Prospect/Order/Task ID) so that a status update can be
     * obtained (e.g. customer onboarding).
     */
    private String temporaryId;

    /**
     * Current status of an order (e.g.customer onboarding to get an update if a customer object
     * already has been opened or not).
     */
    private CurrentStatus currentStatus;

    /**
     * Some kind of permanent ID (Customer/Person/Prospect etc.) so that an object can be
     * identified.
     */
    private String permanentId;
}
