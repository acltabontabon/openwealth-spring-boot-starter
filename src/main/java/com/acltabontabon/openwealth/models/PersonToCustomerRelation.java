package com.acltabontabon.openwealth.models;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonToCustomerRelation {

    private List<Relation> relationList;

    private Person personDetails;

}
