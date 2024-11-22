package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;

@Data
public class Kyc {

    private String externalReference;
    private String purposeOfRelationship;
    private String additionalInformationRelationship;
    private Boolean soleBeneficialOwner;
    private FundFlows fundFlows;
    private List<SourceOfWealth> sourceOfWealthList;

    // TODO:
}
