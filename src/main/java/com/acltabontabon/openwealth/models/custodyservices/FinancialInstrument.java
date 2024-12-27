package com.acltabontabon.openwealth.models.custodyservices;

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
public class FinancialInstrument {

    private FinancialInstrumentIdentification financialInstrumentIdentification;

    /**
     * Name of the financial instrument in free format text.
     */
    private String financialInstrumentName;

    /**
     * Indicates the type of the financial instrument. Must follow the ISO 10962, which is also
     * known as CFI (classification of financial instruments). At least indicate the CFI Category
     * (1st character) and the CFI Group (2nd character). The CFI attributes 1-4 (3rd to 6th
     * character in the string) are optional.
     */
    private String financialInstrumentType;

    @Singular("addInterest")
    private List<Interest> interestList;

    @Singular("addDate")
    private List<FinancialInstrumentDate> dateList;

    private OptionDetails optionDetails;

    /**
     * Contract size of the instrument
     */
    private String contractSize;

    /**
     * Indicates the minimum denomination of a security.
     */
    private String minimumDenomination;

    /**
     * Indicates the minimum tradable increments of a security.
     */
    private String minimumIncrement;

    /**
     * Indicates if there is a factor present for this financial instrument. If this indicator is
     * set to TRUE, but the factor attribute is not present, might indicate, that the factor cannot
     * be delivered or is currently not available.
     */
    private Boolean hasFactor;

    /**
     * Information regarding the factor.
     */
    private String factor;

    /**
     * Provides information on different prices that are related to the functionality of the
     * financial instrument.
     */
    @Singular("addFinancialInstrumentPrice")
    private List<FinancialInstrumentPrice> financialInstrumentPriceList;

    /**
     * Indicates if the instrument is inactive.
     */
    private Boolean inactiveIndicator;

    /**
     * List of underlying instruments.
     */
    private List<FinancialInstrument> underlyingList;

    /**
     * Provides additional information about the financial instrument in narrative form.
     */
    private String financialInstrumentAttributeAdditionalDetails;
}
