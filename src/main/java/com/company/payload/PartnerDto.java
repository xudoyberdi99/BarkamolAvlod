package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PartnerDto {
    @NotBlank(message = "Please provide a name")
    private String name;

    private Long imageId;
}
