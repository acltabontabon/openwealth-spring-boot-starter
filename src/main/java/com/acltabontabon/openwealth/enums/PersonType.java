package com.acltabontabon.openwealth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PersonType {

    NATURAL_PERSON("naturalPerson"),
    LEGAL_PERSON("legalPerson"),
    PERSON_ASSOCIATION("personAssociation");

    private final String value;

    PersonType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PersonType forValue(String value) {
        for (PersonType personType : PersonType.values()) {
            if (personType.value.equalsIgnoreCase(value)) {
                return personType;
            }
        }

        throw new IllegalArgumentException("Invalid person type: " + value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
