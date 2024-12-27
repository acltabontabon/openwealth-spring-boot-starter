package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(Include.NON_EMPTY)
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
     * FIXME: Typo from OpenWealth API specs! Remove override once fixed.
     */
    @JsonProperty("additonalInformation")
    private String additionalInformation;

    /**
     * Source of income.
     */
    @Singular("addSourceOfIncome")
    private List<SourceOfIncome> sourceOfIncomeList;

}
