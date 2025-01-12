package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.DayCountBasis;
import com.acltabontabon.openwealth.types.InterestType;
import com.acltabontabon.openwealth.types.PaymentFrequency;
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
     */
    private InterestType type;

    /**
     * Interest method of the instrument.
     */
    private DayCountBasis dayCountBasis;

    /**
     * Date of the next interest payment. Full-date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private String paymentDate;

    /**
     * Specifies the frequency of an interest payment.
     */
    private PaymentFrequency paymentFrequency;

}
