package com.acltabontabon.openwealth.models.orderplacement;

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
public class BillingDetails {

    @Singular("addBillingAmount")
    private List<BillingAmount> billingAmountList;

    private AccruedInterests accruedInterests;

    @Singular("addFeeOrTax")
    private List<FeeOrTax> feesOrTaxList;
}
