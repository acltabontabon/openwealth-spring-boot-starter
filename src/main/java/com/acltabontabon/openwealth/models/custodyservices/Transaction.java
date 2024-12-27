package com.acltabontabon.openwealth.models.custodyservices;

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
public class Transaction {

    /**
     * Unique and unambiguous identification used by the bank for the customer.
     */
    private String customerIdentification;

    /**
     * Transaction ID given by the bank
     */
    private String transactionIdentification;

    /**
     * Market in which a trade transaction is to be or has been executed.
     */
    private PlaceOfTrade placeOfTrade;

    /**
     * Indicates whether it is the reversal of a previously reported movement.
     */
    private Boolean reversalIndicator;

    /**
     * States the identification of the transaction that was reversed
     */
    private String reversedTransactionIdentification;

    @Singular("addDate")
    private List<Date> dateList;

    /**
     * Type of the transaction.
     * FIXME: This should be an enum
     */
    private String transactionType;

    /**
     * Gives detailed information about the category of a transaction.
     * FIXME: This should be an enum
     */
    private String transactionSubType;

    /**
     * Security triggering/causing the transaction.
     */
    private TriggeringFinancialInstrument triggeringFinancialInstrument;

    /**
     * List of movements belonging to a transaction from a banks perspective.
     */
    @Singular("addMovement")
    private List<Movement> movementList;

    /**
     * List of total amounts of money that is to be/was posted to respective accounts in the
     * account currency.
     */
    @Singular("addPostingAmount")
    private List<PostingAmount> postingAmountList;

    @Singular("addForeignExchangeRate")
    private List<ForeignExchangeRate> foreignExchangeRateList;

    /**
     * ISO 4217 code of the currency used for the settlement of the transaction.
     */
    private String settlementCurrency;

    @Singular("addPrice")
    private List<TransactionPrice> priceList;

    /**
     * Provides additional details on the transaction which can not be included within the
     * structured fields of the message.
     */
    private String transactionAdditionalDetails;
}
