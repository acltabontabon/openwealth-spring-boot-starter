package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioInformation {

    /**
     * Unique and unambiguous identification for the portfolio between the portfolio owner and the
     * portfolio servicer.
     */
    private String portfolioIdentification;

    /**
     * ISO 4217 code of the currency used by the portfolio.
     */
    private String portfolioReferenceCurrency;

}
