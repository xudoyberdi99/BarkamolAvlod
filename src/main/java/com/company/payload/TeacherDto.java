package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class TeacherDto {
    @NotBlank(message="Please provide a name" )
    private String fullName;
    @NotBlank(message ="Please provide a phoneNumber" )
    private String phoneNumber;
    @NotBlank(message="Please provide a achievements" )
    private String achievements;

    private Long imageId;

    private List<Long> subjects;
}
