package com.acltabontabon.openwealth.models.custodyservices;

import com.acltabontabon.openwealth.types.DateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Date {

    private String date;

    private DateType dateType;
}
