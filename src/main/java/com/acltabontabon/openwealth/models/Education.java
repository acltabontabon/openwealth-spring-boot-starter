package com.acltabontabon.openwealth.models;

import lombok.Data;

@Data
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
