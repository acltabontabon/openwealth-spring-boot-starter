package com.acltabontabon.openwealth.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    public static final String HEADER_LIMIT = "limit";

    public static final String MDC_CORRELATION_ID = "correlationId";
}
