package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class SourceOfIncome {

    /**
     * Type of source of income.
     */
    private String type;

    /**
     * Amount.
     */
    private Long amount;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * Industry of employment/source of income.
     */
    private String industry;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;
}
