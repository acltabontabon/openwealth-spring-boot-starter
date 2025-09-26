package com.acltabontabon.openwealth.services.orderplacement;

import com.acltabontabon.openwealth.services.orderplacement.access.AccountAccessReader;
import com.acltabontabon.openwealth.services.orderplacement.status.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderPlacementService {

    private final OrderPlacementComponentFactory componentFactory;

    public OrderReader orders() {
        return componentFactory.createOrderReader();
    }

    public AccountAccessReader accountAccesses() {
        return componentFactory.createAccountAccessReader();
    }
}
