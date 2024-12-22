package com.acltabontabon.openwealth.model;

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
public class InitialAmount {

    /**
     * Amount.
     */
    private Long amount;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * 2-Letter ISO 3166-2 Country Code to indicate the country of origin of the assets.
     */
    @Singular("addOriginOfAssets")
    private List<String> originsOfAssetsList;

    /**
     * Description origin of assets - how were these assets generated/acquired?
     */
    private String originOfAssetsDetails;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfBank;

    /**
     * 2-Letter ISO 3166-2 Country Code
     */
    private String domicileOfBank;

    /**
     * Transfer of amount by cash deposit or physical delivery of securities/precious metals.
     */
    private Boolean physicalTransfer;

    /**
     * Transfer of amount/securities/precious metals by electronic transfer.
     */
    private Boolean electronicTransfer;

    /**
     * Assets are transferred by same person/beneficial owner as the account/beneficial owner of
     * the customer relationship.
     */
    private Boolean samePerson;

    /**
     * In case the assets do not originate from the same person/BO - Name of the third party who
     * transfers the assets.
     */
    private String thirdPartyName;

    /**
     * Relationship of that third party to account/beneficial owner.
     */
    private String thirdPartyRelationship;

    /**
     * Reasons for transfer of assets from a third party.
     */
    private String thirdPartyReason;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

}
