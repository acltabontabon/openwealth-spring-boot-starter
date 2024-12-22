package com.acltabontabon.openwealth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
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
@JsonInclude(Include.NON_EMPTY)
public class Prospect {

    /**
     * Given name(s) of the person. The primary name(s) according to the passport should be
     * indicated in that field and no abbreviations/common names (Dan instead of Daniel etc.) used.
     */
    @JsonProperty("givenName")
    private String firstName;

    /**
     * Last name(s) of the person. The last name(s) according to the passport should be indicated
     * in that field (hyphenated and spaced names as well).
     */
    private String lastName;

    /**
     * Country/Countries where a person was born or is legally accepted as belonging to the country.
     * The first country in the list represents the main nationality.
     */
    @Singular
    @JsonProperty("nationalityList")
    private List<String> nationalities;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format/
     */
    @JsonProperty("dateOfBirth")
    private LocalDate birthdate;

    /**
     * 2-Letter ISO 3166-2 Country Code.
     */
    private String domicile;

}
