package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.FxType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: Move to common package
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignExchangeRate {

    /**
     * Currency of the amount to be converted in a currency conversion.
     */
    private String sourceCurrency;

    /**
     * Indicates the rate of a fx transaction for one source currency unit to the target currency.
     * E.g. 1GBP=xxxEUR, where the source currency is GBP and the target currency is EUR.
     */
    private String rate;

    /**
     * Currency into which an amount is to be converted in a currency conversion.
     */
    private String targetCurrency;

    /**
     * Defines the type of the indicated fx rate, whether it’s a real (traded) price or a
     * calculatory price. If currencies are effectively exchanged (real fx), then it should be
     * indicated by either 'costPrice' (including fees, tax) or 'dealPrice'(plain fx without fees,
     * tax). If the foreignExchangeRate is provided for calculatory purposes, e.g. for position
     * valuations in another currency than the position itself, then 'calculatory' should be used.
     */
    private FxType fxType;
}
