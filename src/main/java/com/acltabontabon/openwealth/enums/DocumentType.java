package com.acltabontabon.openwealth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DocumentType {

    ID("id"),
    PASSPORT("passport"),
    IDENTITY_CARD("identityCard"),
    RESIDENCE_PERMIT("residencePermit"),
    STUDENT_CARD("studentCard"),
    CONFIRMATION_OF_DOMICILE("confirmationOfDomicile"),
    DRIVER_LICENSE("driverLicense"),
    BASIC_CONTRACT("basicContract"),
    POWER_OF_ATTORNEY("powerOfAttorney"),
    DECLARATION_OF_SOLIDARITY("declarationOfSolidarity"),
    TERMS_AND_CONDITIONS("termsAndConditions"),
    EAM_CLIENT_PROFILE("eamClientProfile"),
    EAM_POWER_OF_ATTORNEY("eamPowerOfAttorney"),
    DECLARATION_OF_CONSENT("declarationOfConsent"),
    START_OF_BUSINESS_RELATIONSHIP("startOfBusinessRelationship"),
    EBANKING_CONTRACT("ebankingContract"),
    FORM_A("formA"),
    FORM_R("formR"),
    FORM_I("formI"),
    SELF_DISCLOSURE_TAX_CERTIFICATION("selfDisclosureTaxCertification"),
    US_WAIVER_AND_CERTIFICATION("usWaiverAndCertification"),
    FORM_W8BEN("formW8ben"),
    FORM_W9("formW9"),
    MAILING_INSTRUCTIONS("mailingInstructions"),
    SPECIAL_SHIPPING_INSTRUCTION("specialShippingInstruction"),
    CHANGE_OF_ADDRESS("changeOfAddress"),
    OTHER("other");

    private final String value;

    DocumentType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DocumentType forValue(String value) {
        for (DocumentType documentType : DocumentType.values()) {
            if (documentType.value.equalsIgnoreCase(value)) {
                return documentType;
            }
        }

        throw new IllegalArgumentException("Invalid document type: " + value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
