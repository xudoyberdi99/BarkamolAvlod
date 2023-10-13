package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class SubjectDto {
    @NotBlank(message="Please provide a name" )
    private String name;
    @NotBlank(message ="Please provide a description" )
    private String description;

    private Long courseId;

}
