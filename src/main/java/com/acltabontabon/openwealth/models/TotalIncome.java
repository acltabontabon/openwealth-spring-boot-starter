package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

@Data
public class TotalIncome {

    /**
     * Amount.
     */
    private Long amountYearlyIncome;

    /**
     * ISO 4217 Currency Code
     */
    private String currency;

    /**
     * Year.
     */
    private String referenceYear;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

    /**
     * Source of income.
     */
    private List<SourceOfIncome> sourceOfIncomeList;

}
