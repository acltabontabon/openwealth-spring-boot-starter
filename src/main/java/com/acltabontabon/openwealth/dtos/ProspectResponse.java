package com.acltabontabon.openwealth.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ProspectResponse {

    /**
     * Some kind of ID (Customer/Person/Prospect/Order/Task ID) so that a status update can be
     * obtained (e.g. customer onboarding).
     */
    private String temporaryId;

    /**
     * Result of the conducted prospect pre-check. The result is in no way binding and may change
     * during the process.
     */
    // FIXME: This should be an enum
    private String response;

}
