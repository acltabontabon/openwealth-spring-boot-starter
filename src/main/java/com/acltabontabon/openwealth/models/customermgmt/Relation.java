package com.acltabontabon.openwealth.models.customermgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Relation {

    /**
     * Indicates the kind of relation the person has to this customer relationship.
     */
    @NonNull
    private String type;

    /**
     * Defines the level of rights of the authorised signatory and indicates if the person has the
     * right to sign individually or collectively (in twos, etc.)
     */
    private String cardinality;

    /**
     * Field to indicate the customer to person relation type. Field shall only be used if the
     * relation type is not part of the ENUM of the attribute "relation" hence in exceptional
     * cases (in case of relation type "other").
     */
    private String override;

}
