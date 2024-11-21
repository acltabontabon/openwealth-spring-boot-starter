package com.acltabontabon.openwealth.model;

public enum TypeCategory {

    IDENTIFICATION("identification"),
    BASIC_DOCUMENTS("basicDocuments"),
    PRODUCTS("products"),
    TAX_DOCUMENTS("taxDocuments"),
    REGULATORY_DOCUMENTS("regulatoryDocuments"),
    CLIENT_CORRESPONDENCE("clientCorrespondence"),
    OTHER("other");

    private String value;

    TypeCategory(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
