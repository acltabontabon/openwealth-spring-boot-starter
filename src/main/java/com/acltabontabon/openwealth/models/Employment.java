package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

/**
 * Provides information about the employment status and the employment itself (e.g. company).
 */
@Data
public class Employment {

    /**
     * Status of employment.
     */
    private String employmentStatus;

    /**
     * List of employment details.
     */
    private List<EmploymentDetails> employmentDetailsList;
}
