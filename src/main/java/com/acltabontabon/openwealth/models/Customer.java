package com.acltabontabon.openwealth.models;

import com.acltabontabon.openwealth.enums.RelationshipStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Customer {

    /**
     * Unique customer ID assigned by the custody bank.
     */
    @NonNull
    private String customerId;

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    @NonNull
    private String name;

    /**
     * Status of the customer relationship.
     * Enum: "active" "inactive"
     */
    private RelationshipStatus status;

    /**
     * ISO 4217 Currency Code.
     */
    private String referenceCurrency;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate openingDate;

    /**
     * 2-letter ISO 639-1 Language Code.
     */
    private String language;

    /**
     * Customer segment of the customer at the custody bank (e.g. UHNWI Switzerland).
     */
    private String customerSegment;

    /**
     * Indicates the instruction/full name of the bank advisor on the side of the custody bank.
     */
    private String bankAdvisor;

    /**
     * Indicates the instruction/full name of the bank deputy advisor on the side of the custody
     * bank.
     */
    private String bankDeputyAdvisor;

    /**
     * Indicates the instruction/full name of the previous bank advisor on the side of the custody
     * bank.
     */
    private String bankPreviousAdvisor;

    /**
     * List of documents belonging to this customer relationship.
     */
    @Singular("addDocument")
    private List<Document> documentList;

    @Singular("addPersonToCustomerRelation")
    private List<PersonToCustomerRelation> person2customerRelationList;
}
