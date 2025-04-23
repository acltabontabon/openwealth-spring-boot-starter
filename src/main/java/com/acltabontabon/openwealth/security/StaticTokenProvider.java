package com.acltabontabon.openwealth.security;

import com.acltabontabon.openwealth.properties.OpenWealthApiProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticTokenProvider implements TokenProvider {

    private final OpenWealthApiProperties openWealthApiProperties;

    @Override
    public String getAccessToken() {
        return openWealthApiProperties.getAccessToken();
    }
}