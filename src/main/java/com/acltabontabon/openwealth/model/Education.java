package com.acltabontabon.openwealth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Education {

    /**
     * Highest degree of education.
     */
    private String highestDiploma;

    /**
     * Year.
     */
    private String graduationYear;

    /**
     * Institute at which graduation took place.
     */
    private String institute;

    /**
     * Field of study.
     */
    private String studyProgramme;
}
