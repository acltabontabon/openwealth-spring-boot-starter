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
public class Kyc {

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * Description of purpose of relationship e.g. Asset Management by EAM.
     */
    private String purposeOfRelationship;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformationRelationship;

    /**
     * Is the person the sole beneficial owner of this customer relationship?
     */
    private Boolean soleBeneficialOwner;

    /**
     * Fund flows of the person.
     */
    private FundFlows fundFlows;

    /**
     * Source of wealth of the person.
     */
    @Singular("addSourceOfWealth")
    private List<SourceOfWealth> sourceOfWealthList;

    /**
     * Total wealth of the person.
     */
    private TotalWealth totalWealth;

    /**
     * Total income of the person.
     */
    private TotalIncome totalIncome;

    /**
     * Provides information about the employment status and the employment itself (e.g. company)
     */
    private Employment employment;

    /**
     * Education of the person.
     */
    private Education education;

    /**
     * Provides indications if a person might be a corporate insider.
     */
    @Singular("addCorporateInsiderPerson")
    private List<CorporateInsiderPerson> corporateInsiderPersonList;

    /**
     * Provides information about the relationship between the person and a corporate insider.
     */
    @Singular("addCorporateInsiderRelation")
    private List<CorporateInsiderRelation> corporateInsiderRelationList;

    /**
     *
     */
    @Singular("addMajorShareholderPerson")
    private List<MajorShareholder> majorShareholderPersonList;

    /**
     *
     */
    @Singular("addMajorShareholderRelation")
    private List<MajorShareholderRelation> majorShareholderRelationList;

    /**
     * Provides information about the political status of the person.
     */
    private PoliticalStatus politicalStatus;

    /**
     *
     */
    private Fatca fatca;

    /**
     *
     */
    private ExternalAssetManager externalAssetManager;

    /**
     *
     */
    private FurtherDetails furtherDetails;

}
