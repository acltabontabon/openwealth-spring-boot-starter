package com.acltabontabon.openwealth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public class FundFlows {

    /**
     * Amount.
     */
    private Long amountExpectedInflows;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyExpectedInflows;

    /**
     * Amount.
     */
    private Long amountPlannedTotalAssets;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyPlannedTotalAssets;

    /**
     * Amount.
     */
    private Long amountExpectedTurnover;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyExpectedTurnover;

    /**
     * Number.
     */
    private Integer numberOfInflows;

    /**
     * Number.
     */
    private Integer numberOfOutflows;

    /**
     * List of recurring counter-parties.
     */
    @Singular("addRecurringCounterparty")
    private List<RecurringCounterParty> recurringCounterpartyList;

    /**
     * List of initial amounts.
     */
    @Singular("addInitialAmount")
    private List<InitialAmount> initialAmountList;

    /**
     * List of expected fund in- / outflows after the deposit of the initial amount.
     */
    @Singular("addExpectedFundFlow")
    private List<ExpectedFundFlow> expectedFundFlowList;
}
