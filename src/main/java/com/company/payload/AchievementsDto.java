package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AchievementsDto {
    @NotBlank(message="Please provide a name" )
    private String name;
    @NotBlank(message="Please provide a body" )
    private String body;
    @NotBlank(message ="Please provide a description" )
    private String description;
    @NotNull(message ="Please provide a imageId" )
    private Long imagesId;
}
