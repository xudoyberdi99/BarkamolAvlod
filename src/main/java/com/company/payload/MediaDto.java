package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MediaDto {

    private Long vedioId;
    @NotBlank(message ="Please provide a description" )
    private String description;

}
