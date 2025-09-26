package com.acltabontabon.openwealth.services.custodyservices;

import com.acltabontabon.openwealth.services.custodyservices.account.AccountReader;
import com.acltabontabon.openwealth.services.custodyservices.customer.CustomerReader;
import com.acltabontabon.openwealth.services.custodyservices.position.PositionReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustodyService {

    private final CustodyServicesComponentFactory componentFactory;

    public CustomerReader customers() {
        return componentFactory.createCustomerReader();
    }

    public AccountReader accounts() {
        return componentFactory.createAccountReader();
    }

    public PositionReader positions() {
        return componentFactory.createPositionReader();
    }

}
