package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * Provides information about the employment status and the employment itself (e.g. company).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Employment {

    /**
     * Status of employment.
     */
    private String employmentStatus;

    /**
     * List of employment details.
     */
    @Singular("addEmploymentDetails")
    private List<EmploymentDetails> employmentDetailsList;
}
