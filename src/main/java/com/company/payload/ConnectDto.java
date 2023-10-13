package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConnectDto {
    @NotBlank(message="Please provide a address" )
    private String address;
    @NotBlank(message ="Please provide a phoneNumber" )
    private String phoneNumber;
    @NotBlank(message ="Please provide a email" )
    private String email;
    @NotBlank(message ="Please provide a telegram" )
    private String telegramLink;
    @NotBlank(message ="Please provide a instagram" )
    private String instagramlink;
    @NotBlank(message ="Please provide a facebook" )
    private String facebook;

}
