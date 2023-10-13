package com.company.service;

import com.company.entity.Documents;
import com.company.payload.ApiResponse;
import com.company.payload.DocumentsDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DocumentsService {
    ApiResponse addDocument(DocumentsDto documentsDto);

    ApiResponse editDocument(Long id, DocumentsDto documentsDto);

    ApiResponse deleteDocument(Long id);

    List<Documents> getAllDocument();

    Page<Documents> getAllDocumentWithPage(int page, int size);

    Documents DocumentById(Long id);
}
