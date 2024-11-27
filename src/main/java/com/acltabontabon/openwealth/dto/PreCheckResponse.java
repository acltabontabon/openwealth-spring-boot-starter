package com.acltabontabon.openwealth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreCheckResponse implements ApiResponse {

    private String temporaryId;
    private String status;

}
