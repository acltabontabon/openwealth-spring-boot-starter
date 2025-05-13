package com.acltabontabon.openwealth.models.custodyservices;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceOfTrade {

    /**
     * Market Identifier Code. Identification of a financial market, as stipulated in the norm
     * ISOMarket Identifier Code. Identification of a financial market, as stipulated in the norm
     * ISO 10383 "Codes for exchanges and market identifications".
     */
    private String marketIdentificationCode;

    /**
     * Description of the market when no Market Identification Code is available.
     */
    private String marketDescription;

}
