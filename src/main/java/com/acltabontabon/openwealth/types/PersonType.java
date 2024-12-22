package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
    @JsonValue
    public String toString() {
        return this.value;
    }
}
