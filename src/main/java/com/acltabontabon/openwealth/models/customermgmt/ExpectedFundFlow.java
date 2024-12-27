package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_EMPTY)
public class ExpectedFundFlow {

    /**
     * Amount.
     */
    private Long amount;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * Type of account movement.
     */
    private String type;

    /**
     * Name of the sender/receiver of the assets.
     */
    private String counterparty;

    /**
     * Purpose for the transfer.
     */
    private String purpose;

    /**
     * Frequency of payment/transfer.
     */
    private String frequency;

    /**
     * 2-Letter ISO 3166-2 Country Code to indicate the country of origin of the assets.
     */
    @Singular("addOriginOfAssets")
    private List<String> originsOfAssetsList;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfBank;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String domicileOfBank;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

}
