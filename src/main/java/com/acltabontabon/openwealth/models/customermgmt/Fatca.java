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
public class Fatca {

    /**
     * Is the person a US person?
     */
    private Boolean status;

    /**
     * Is the person currently in the United States?
     */
    private Boolean domicile;

    /**
     * Was the person born in the United States or a U.S. territory?
     */
    private Boolean birthplace;

    /**
     * Is the person a holder of an active U.S. Green Card?
     */
    private Boolean greenCard;

    /**
     * Is the person considered a U.S. resident for tax purposes due to substantial presence test?
     */
    private Boolean substantialPresenceTest;

    /**
     * Free text field for further details on FATCA status.
     */
    private Boolean otherReasons;
}
