package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BAMDto {
    @NotBlank(message = "Please provide a title")
    private String title;
    @NotBlank(message="Please provide a address" )
    private String fullAddress;
    @NotBlank(message ="Please provide a phoneNumber" )
    private String phoneNumber;
    @NotBlank(message ="Please provide a DirektorName" )
    private String direktorName;

}
