package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotBlank(message="Please provide a username" )
    private String username;
    @NotBlank(message="Please provide a password" )
    private String password;
}
