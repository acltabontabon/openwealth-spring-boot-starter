package com.acltabontabon.openwealth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Source {

    /**
     * Original source of assets.
     */
    private String sourceType;

    // --- Income From Employment ---
    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String companyName;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String companyDomicile;

    /**
     * Description of the business activity of the company and industry (e.g. NOGA Code).
     */
    private String companyBusinessActivity;

    /**
     * Number of employees of company.
     */
    private Integer companyNumberOfEmployees;

    /**
     * Amount.
     */
    private Long companyAnnualTurnover;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyAnnualTurnover;

    /**
     * Amount.
     */
    private Long companyAnnualProfit;

    /**
     * ISO 4217 Currency Code.
     */
    private String currencyAnnualProfit;

    /**
     * Function/position which the person helds at the company.
     */
    private String roleOrPosition;

    /**
     * Period of time e.g. a job was held / a company owned.
     */
    private String period;

    /**
     * Free text field to provide additional information.
     */
    private String additionalInformation;

    // --- Income From Business ---
    /**
     * Origin of start capital in case of business ownership / acquisition.
     */
    private String startCapitalOrigin;

    /**
     * Year.
     */
    private String companyFoundingYear;

    /**
     * Percentage of shareholding in the company.
     */
    private Integer shareholdingPercent;

    /**
     * Names of other shareholders of that company.
     */
    private String namesOfOtherShareholders;

    // --- Sale Of Company ---
    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate dateOfSale;

    /**
     * Buyer of the company.
     */
    private String buyer;

    // --- Sale Of Non-Financial Assets
    /**
     * Description of asset.
     */
    private String details;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate dateOfAcquisition;

    /**
     * Amount.
     */
    private Long initialValue;

    /**
     * ISO 4217 Currency Code.
     */
    private String currency;

    /**
     * Method of sale of asset (e.g. sale of plane via auction in London).
     */
    private String methodOfSaleOfAsset;

    // --- Life Insurance ---
    /**
     * Details about the life insurance.
     */
    private String type;

    /**
     * Name of the provider.
     */
    private String provider;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate dateOfReception;

    // --- Pension Assets ---
    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate dateOfRetirement;
    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String lastEmployer;

    // --- Inheritance ---
    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfTestator;

    /**
     * Relation of the person to the testator.
     */
    private String relationToTestator;

    /**
     * Original origin of the assets of the testator - how was the wealth generated?.
     */
    private String originOfAssetsOfTestator;

    /**
     * Nature of the assets received.
     */
    private String natureOfAssets;

    // --- Gift ---
    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfGiftor;

    /**
     * Relation of the person to the giftor.
     */
    private String relationToGiftor;

    /**
     * Original origin of the assets of the giftor - how was the wealth generated?
     */
    private String originOfAssetsOfGiftor;

    // --- Loan ---
    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String nameOfLendor;

    /**
     * Relation of the person to the lendor.
     */
    private String relationToLendor;

    /**
     * Original origin of the assets of the lendor - how was the wealth generated?
     */
    private String originAssetsOfLendor;

    /**
     * Purpose of loan.
     */
    private String purposeOfLoan;

    // --- Sale of Financial Assets ---
    /**
     * Origin of acquisition of asset - When and how were the assets bought?
     */
    private String acquisitionOfAsset;

    /**
     * Name of the bank/broker over which the assets were sold.
     */
    private String nameBankOrBroker;
}
