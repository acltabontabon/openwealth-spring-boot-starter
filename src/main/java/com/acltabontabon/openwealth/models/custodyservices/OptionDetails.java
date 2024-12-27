package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDetails {

    /**
     * Specifies whether it is a Call option (right to purchase a specific underlying asset) or a
     * Put option (right to sell a specific underlying asset).
     * FIXME: This should be an enum.
     */
    private String optionType;

    /**
     * Specifies how an option can be exercised.
     * FIXME: This should be an enum.
     */
    private String optionStyle;

}
