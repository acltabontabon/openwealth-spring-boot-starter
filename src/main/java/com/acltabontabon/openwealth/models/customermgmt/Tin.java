package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
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
