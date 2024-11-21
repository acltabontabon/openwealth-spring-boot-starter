package com.acltabontabon.openwealth.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class Person {

    /**
     * Unique person ID assigned by the custody bank.
     */
    private String personId;

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * 2-letter ISO 639-1 Language Code.
     */
    private String language;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String countryOfDomicile;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate openingDate;

    /**
     * List with tax domiciles of a person.
     * 2-Letter ISO 3166-2 Country Code.
     */
    private List<String> taxDomicileList;

    /**
     * Indicates the person type (e.g. Natural Person) of the person object.
     */
    private PersonType personType;

    /**
     * Given name of the person. The primary name according to the passport should be indicated in
     * that field and no abbreviations/common names (Dan instead of Daniel etc.) used.
     */
    private String givenName;

    /**
     * Middle name of the person. The middle name according to the passport should be indicated in
     * that field and no abbreviations/common names (Dan instead of Daniel etc.) used.
     */
    private String middleName;

    /**
     * Last name(s) of the person. The last name(s) according to the passport should be indicated
     * in that field (hyphenated and spaced names as well).
     */
    private String lastName;

    /**
     * Official title of the person.
     */
    private String title;

    /**
     * Birthdate of the person.
     */
    private LocalDate dateOfBirth;

    /**
     * Marriage date of the person.
     */
    private LocalDate dateOfMarriage;

    /**
     * Death date of the person.
     */
    private LocalDate dateOfDeath;

    /**
     * Gender of the person.
     */
    private String gender;

    /**
     * Country/Countries where a person was born or is legally accepted as belonging to the country.
     * The first country in the list represents the main nationality.
     */
    private List<String> nationalityList;

    /**
     * Civil status of the person.
     */
    private CivilStatus civilStatus;

    /**
     * List with tax identification numbers of a person.
     */
    private List<Tin> tinList;

    /**
     * Country where person was born.
     */
    private String countryOfBirth;
}
