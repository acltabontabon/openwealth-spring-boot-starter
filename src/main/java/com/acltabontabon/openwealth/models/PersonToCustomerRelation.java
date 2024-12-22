package com.acltabontabon.openwealth.models;

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
public class PersonToCustomerRelation {

    @Singular("addRelation")
    private List<Relation> relationList;

    private Person personDetails;

}
