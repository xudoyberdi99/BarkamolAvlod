package com.company.controller;

import com.company.entity.Course;
import com.company.entity.Documents;
import com.company.payload.ApiResponse;
import com.company.payload.DocumentsDto;
import com.company.service.CourseService;
import com.company.service.DocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api")
public class DocumentsController {
    @Autowired
    private DocumentsService documentsService;

    @PostMapping("/document/add")
    public ResponseEntity<?> addDocument(@Valid @RequestBody DocumentsDto documentsDto){
        ApiResponse apiResponse=documentsService.addDocument(documentsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/document/edit/{id}")
    public HttpEntity<?> editDocument(@Valid @PathVariable Long id, @RequestBody DocumentsDto documentsDto){
        ApiResponse apiResponse=documentsService.editDocument(id,documentsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/document/delete/{id}")
    public HttpEntity<?> deleteDocument(@PathVariable Long id){
        ApiResponse apiResponse=documentsService.deleteDocument(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/document/all")
    public HttpEntity<?> getallDocument(){
        List<Documents> documentsList=documentsService.getAllDocument();
        return ResponseEntity.ok(documentsList);
    }
    @GetMapping("/user/document/allWithPage")
    public HttpEntity<?> getallDocumentWithPage(int page, int size){
        Page<Documents> documents=documentsService.getAllDocumentWithPage(page,size);
        return ResponseEntity.ok(documents);
    }
    @GetMapping("/user/document/DocumentById/{id}")
    public HttpEntity<?> DocumentById(@PathVariable Long id){
        Documents documents=documentsService.DocumentById(id);
        return ResponseEntity.ok(documents);
    }
}
