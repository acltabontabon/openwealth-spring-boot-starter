package com.acltabontabon.openwealth.models;

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
public class EmploymentDetails {

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String companyName;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String companyDomicile;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    @Singular("addCompanyCountriesOfBusiness")
    private List<String> companyCountriesOfBusinessList;

    /**
     * Description of the business activity of the company and industry (e.g. NOGA Code).
     */
    private String companyDetail;

    /**
     * Number of employees of company.
     */
    private Integer companyNumberOfEmployees;

    /**
     * Amount.
     */
    private Integer companyAnnualTurnover;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyAnnualTurnover;

    /**
     * Industry of the company / NOGA code of company
     */
    private String industry;

    /**
     * Function/position which the person helds at the company..
     */
    private String roleOrPosition;

    /**
     * Profession.
     */
    private String profession;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String domicile;

    /**
     * Percentage of shareholding in the company.
     */
    private Integer shareholdingInPercent;

    /**
     * Period of time e.g. a job was held / a company owned.
     */
    private String period;

    /**
     * Year.
     */
    private String yearOfRetirement;

    /**
     * Year.
     */
    private String sharedholderSinceYear;

    /**
     * Description of mandate e.g. Member of the Board of Directors; holder of a political or
     * charitable mandate.
     */
    private String mandate;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

}
