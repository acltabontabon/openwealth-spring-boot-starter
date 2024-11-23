package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
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
