package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.Documents;
import com.company.payload.ApiResponse;
import com.company.payload.DocumentsDto;
import com.company.repository.AttachmentRepository;
import com.company.repository.DocumentsRepository;
import com.company.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsServiceImpl implements DocumentsService {
    @Autowired
    private  DocumentsRepository documentsRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public ApiResponse addDocument(DocumentsDto documentsDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(documentsDto.getDocumentId());
        if(!optionalAttachment.isPresent()){
            return  new ApiResponse("not found document file",false);
        }

        Documents documents=new Documents();
        documents.setTitle(documentsDto.getTitle());
        documents.setDocument(optionalAttachment.get());

        documentsRepository.save(documents);
        return new ApiResponse("add document success",true);
    }

    @Override
    public ApiResponse editDocument(Long id, DocumentsDto documentsDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(documentsDto.getDocumentId());
        if(!optionalAttachment.isPresent()){
            return  new ApiResponse("not found document file",false);
        }
        Optional<Documents> optionalDocuments = documentsRepository.findById(id);
        if (!optionalDocuments.isPresent()){
            return new ApiResponse("not found documents", false);
        }

        Documents documents=new Documents();
        documents.setTitle(documentsDto.getTitle());
        documents.setDocument(optionalAttachment.get());

        documentsRepository.save(documents);
        return new ApiResponse("edit document success",true);

    }

    @Override
    public ApiResponse deleteDocument(Long id) {
        try{
            documentsRepository.deleteById(id);
            return new ApiResponse("delete documents",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Documents> getAllDocument() {
        return documentsRepository.findAll();
    }

    @Override
    public Page<Documents> getAllDocumentWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return documentsRepository.findAll(pageable);
    }

    @Override
    public Documents DocumentById(Long id) {
        return documentsRepository.findById(id).orElse(new Documents());
    }
}
