package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class DocumentsDto {
    @NotBlank(message="Please provide a title" )
    private String title;
    private Long documentId;
}
