package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionSubtype {

    ADDITIONAL_PAYMENT("additionalPayment"),
    ADJUST_NOTIONAL("adjustNotional"),
    ASSIGNMENT("assignment"),
    ASSIMILATION("assimilation"),
    BONUS("bonus"),
    BUY("buy"),
    BUY_BARRIER("buyBarrier"),
    BUY_DIGITAL("buyDigital"),
    BUY_ISSUE("buyIssue"),
    BUY_TO_CLOSE("buyToClose"),
    BUY_TO_OPEN("buyToOpen"),
    CAPITAL_INCREASE("capitalIncrease"),
    CLOSE_FX_FORWARD("closeFxForward"),
    CLOSE_NDF("closeNDF"),
    CONVERSION_BOND_SHARE("conversionBondShare"),
    CREDIT_EVENT("creditEvent"),
    DECREASE("decrease"),
    DELIVER_SECURITY_TO_FUND("deliverSecurityToFund"),
    DELIVERY_FREE_OF_PAYMENT("deliveryFreeOfPayment"),
    DELIVERY_VS_PAYMENT("deliveryVsPayment"),
    DIVIDEND_CASH("dividendCash"),
    DIVIDEND_CHOICE("dividendChoice"),
    DIVIDEND_REINVESTMENT("dividendReinvestment"),
    DIVIDEND_STOCK("dividendStock"),
    EXERCISE("exercise"),
    EXERCISE_CASH("exerciseCash"),
    EXERCISE_PHYSICAL("exercisePhysical"),
    EXPIRATION("expiration"),
    EXPIRATION_OLD_SECURITY("expirationOldSecurity"),
    FEES("fees"),
    FINAL_LIQUIDATION_PAYMENT("finalLiquidationPayment"),
    FX_SPOT("fxSpot"),
    INCOMPLETE_RIGHTS("incompleteRights"),
    INCREASE("increase"),
    INFLOW_OUTFLOW_DIGITAL("inflowOutflowDigital"),
    INFLOW_OUTFLOW_PHYSICAL("inflowOutflowPhysical"),
    INSTRUMENT_EXCHANGE("instrumentExchange"),
    INTEREST("interest"),
    INTERNAL_TRANSFER("internalTransfer"),
    KNOCK_IN("knockIn"),
    KNOCK_OUT("knockOut"),
    LIQUIDATION_PAYMENT("liquidationPayment"),
    MARK_TO_MARKET("markToMarket"),
    MARK_TO_MARKET_CASH("markToMarketCash"),
    MERGER("merger"),
    NET_AMOUNT("netAmount"),
    OPEN_FX_FORWARD("openFxForward"),
    OPEN_NDF("openNDF"),
    OPEN_PAYER_SWAP("openPayerSwap"),
    OPEN_RECEIVER_SWAP("openReceiverSwap"),
    OPEN_SWAP_FORWARD_LEG("openSwapForwardLeg"),
    OTHER("other"),
    PREMIUM("premium"),
    PREPAYMENT("prepayment"),
    PUBLIC_OFFER("publicOffer"),
    RECEIVE_FREE_OF_PAYMENT("receiveFreeOfPayment"),
    RECEIVE_FROM_FUND("receiveFromFund"),
    RECEIVE_SECURITY_FROM_FUND("receiveSecurityFromFund"),
    RECEIVE_VS_PAYMENT("receiveVsPayment"),
    REDEMPTION("redemption"),
    REDEMPTION_PARTIAL("redemptionPartial"),
    REDEMPTION_PRIOR("redemptionPrior"),
    REDUCTION_OF_NOMINAL("reductionOfNominal"),
    RESET_PAYMENT("resetPayment"),
    REVENUE("revenue"),
    RIGHT_DISTRIBUTION("rightDistribution"),
    SELL("sell"),
    SELL_BARRIER("sellBarrier"),
    SELL_DIGITAL("sellDigital"),
    SELL_TO_CLOSE("sellToClose"),
    SELL_TO_OPEN("sellToOpen"),
    SPIN_OFF("spinOff"),
    SPLIT("split"),
    SUBSCRIPTION("subscription"),
    SWAP_SPOT_LEG("swapSpotLeg"),
    TAX_CORRECTIONS("taxCorrections"),
    TAXES("taxes"),
    TRANSFER_METAL_PHYSICAL("transferMetalPhysical"),
    UNWIND("unwind"),
    VARIATION_MARGIN("variationMargin");

    private final String value;

    TransactionSubtype(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TransactionSubtype forValue(String value) {
        for (TransactionSubtype transactionSubtype : TransactionSubtype.values()) {
            if (transactionSubtype.value.equals(value)) {
                return transactionSubtype;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
