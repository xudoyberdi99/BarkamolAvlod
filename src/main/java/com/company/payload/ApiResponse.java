package com.company.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean succes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ApiResponse(String message, boolean succes) {
        this.message = message;
        this.succes = succes;
    }
}
