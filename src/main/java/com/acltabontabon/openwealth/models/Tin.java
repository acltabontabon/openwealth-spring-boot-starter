package com.acltabontabon.openwealth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tin {

    /**
     * Taxpayer identification number / National ID.
     */
    private String tinNumber;

    /**
     * Country to which the TIN / national ID belongs to.
     */
    private String country;
}
