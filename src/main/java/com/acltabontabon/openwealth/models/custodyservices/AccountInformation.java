package com.acltabontabon.openwealth.models.custodyservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformation {

    /**
     * Unique and unambiguous identification for the account. The IBAN should NOT be the account
     * identifier.
     */
    private String accountIdentification;

    /**
     * Indicates the type of the account. If the account type is cashAccount, no information on the
     * financial instrument is provided in the respective position in the account.
     */
    //FIXME: This should be an enum
    private String accountType;

    /**
     * Contains the accounts Internatioinal Banking Account Number (IBAN) for a cashAccount if
     * available.
     */
    private String iban;

    /**
     * ISO 4217 code of the currency used by the account.
     */
    private String accountReferenceCurrency;

    /**
     * Name of the account. It provides an additional means of identification, and is designated by
     * the account servicer in agreement with the account owner.
     */
    private String accountName;

    /**
     * Supplementary information on the account. Designated by the account servicer.
     */
    private String accountDesignation;

    private PortfolioInformation portfolioInformation;
}
