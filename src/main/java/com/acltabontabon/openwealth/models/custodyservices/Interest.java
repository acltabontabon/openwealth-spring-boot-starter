package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

    /**
     * Interest rate of the instrument.
     */
    private String rate;

    /**
     * ISO 4217 code of the currency of the interest rate.
     */
    private String currency;

    /**
     * Indicates the type of interest.
     * FIXME: this should be an enum.
     */
    private String type;

    /**
     * Interest method of the instrument.
     * FIXME: this should be an enum.
     */
    private String  dayCountBasis;

    /**
     * Date of the next interest payment. Full-date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private String paymentDate;

    /**
     * Specifies the frequency of an interest payment.
     * FIXME: this should be an enum.
     */
    private String paymentFrequency;

}
