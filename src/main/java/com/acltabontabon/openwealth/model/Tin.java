package com.acltabontabon.openwealth.model;

import lombok.Data;

@Data
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
