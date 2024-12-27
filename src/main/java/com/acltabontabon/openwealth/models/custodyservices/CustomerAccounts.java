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
public class CustomerAccounts {

    private String customerIdentification;

    private String customerReferenceCurrency;

    @Singular("addAccount")
    private List<Account> accountList;

}
