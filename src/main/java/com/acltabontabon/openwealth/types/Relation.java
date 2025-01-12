package com.acltabontabon.openwealth.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Relation {

    IS_PARENT_OF("isParentOf"),
    IS_PARTNER_OF("isPartnerOf"),
    IS_CHILD_OF("isChildOf"),
    IS_RELATED_TO("isRelatedTo"),
    IS_SPOUSE_OF("isSpouseOf"),
    IS_REGISTERED_PARTNER_OF("isRegisteredPartnerOf"),
    IS_GUARDIANSHIP_OF("isGuardianshipOf"),
    HAS_GUARDIANSHIP("hasGuardianship"),
    IS_EMPLOYEE_OF("isEmployeeOf"),
    IS_EMPLOYER_OF("isEmployerOf"),
    IS_INTERMEDIARY_OF("isIntermediaryOf"),
    HAS_INTERMEDIARY("hasIntermediary"),
    IS_FIDUCIARY_OF("isFiduciaryOf"),
    HAS_FIDUCIARY("hasFiduciary"),
    IS_HEIR_OF("isHeirOf"),
    OTHER("other");

    private final String value;

    Relation(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Relation forValue(String value) {
        for (Relation relation : Relation.values()) {
            if (relation.value.equalsIgnoreCase(value)) {
                return relation;
            }
        }
        throw new IllegalArgumentException("Invalid relation type: " + value);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }
}
