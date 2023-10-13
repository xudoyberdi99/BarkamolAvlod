package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class NewsDto {
    @NotBlank(message="Please provide a name" )
    private String name;
    @NotBlank(message = "Please provide a title")
    private String  title;
    @NotBlank(message ="Please provide a description" )
    private String description;
    @NotNull(message ="Please provide a images" )
    private Long imagesId;
}
