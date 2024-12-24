package com.acltabontabon.openwealth.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KycResponse implements ApiResponse {

    private String temporaryId;

    private String externalReference;

}
