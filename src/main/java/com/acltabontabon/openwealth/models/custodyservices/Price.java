package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.CreditDebitType;
import com.acltabontabon.openwealth.types.PriceRepresentation;
import com.acltabontabon.openwealth.types.PriceType;
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
public class Price {

    /**
     * Amount of the price.
     */
    private String amount;

    /**
     * Indicates whether the price amount is depicted as unit price or percentage.
     */
    private PriceRepresentation amountType;

    /**
     * Specifies if an amount is positive or negative.
     */
    private CreditDebitType creditDebitIndicator;

    /**
     * Indicates the type of the price.
     */
    private PriceType positionPriceType;

    /**
     * ISO 4217 code of the currency used by the price.
     */
    private String currency;

    /**
     * Date of the end of day price. Date according to ISO 8601 i.e. YYYY-MM-DD format
     */
    private String date;

    /**
     * Indicates the source of the (market)price.
     */
    private String sourceOfPrice;

    @Singular("addForeignExchangeRate")
    private List<ForeignExchangeRate> foreignExchangeRateList;

    /**
     * Valuation of the position in account currency. Accrued interests are not included in the
     * valuation.
     */
    private Valuation valuation;
}
