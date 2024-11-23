package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

@Data
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
     *
     */
    private List<RecurringCounterParty> recurringCounterPartyList;

    /**
     *
     */
    private List<InitialAmount> initialAmountList;

    /**
     * List of expected fund in- / outflows after the deposit of the initial amount.
     */
    private List<ExpectedFundFlow> expectedFundFlowList;
}
