package com.acltabontabon.openwealth.models;

import com.acltabontabon.openwealth.enums.CivilStatus;
import com.acltabontabon.openwealth.enums.PersonType;
import java.time.LocalDate;
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
public class Person {

    /**
     * List of addresses associated with this person.
     */
    @Singular("addAddress")
    private List<Address> addressList;

    /**
     * List of contact details associated with this person.
     */
    @Singular("addContact")
    private List<Contact> contactList;

    /**
     * List of KYC objects associated with this person.
     */
    private List<Kyc> kycList;

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


    //--------------------------------- Natural Person --------------------------------- //

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
     * Gender of the person.
     */
    private String gender;

    /**
     * Country where person was born.
     */
    private String countryOfBirth;

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
     * Country/Countries where a person was born or is legally accepted as belonging to the country.
     * The first country in the list represents the main nationality.
     */
    @Singular("addNationality")
    private List<String> nationalityList;

    /**
     * List with tax identification numbers of a person.
     */
    @Singular("addTin")
    private List<Tin> tinList;

    /**
     * Civil status of the person.
     */
    private CivilStatus civilStatus;

    //--------------------------------- Legal Person --------------------------------- //

    /**
     * Name by which the organisation is known and which is usually used to identify that
     * organisation.
     */
    private String organisationName;

    /**
     * Legal form of the company.
     */
    private String legalForm;

    /**
     * Legal entity identifier. The LEI code is a twenty-digit alphanumeric company identifier that
     * is established as an international standard for companies in the financial market. Each LEI
     * code is assigned once and enables worldwide assignment to a specific company.
     */
    private String LEI;

    /**
     * Indicates whether the company is a domiciliary company.
     */
    private Boolean domicilaryCompany;

    //--------------------------------- Person Association --------------------------------- //

    /**
     * Name of the association object.
     */
    private String personAssociationName;

    /**
     * Type of association.
     */
    private String personAssociationType;
}
