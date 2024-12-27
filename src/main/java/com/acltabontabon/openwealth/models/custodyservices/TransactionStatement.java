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
public class TransactionStatement {

    private Statement statement;

    @Singular("addTransaction")
    private List<Transaction> transactions;

}
