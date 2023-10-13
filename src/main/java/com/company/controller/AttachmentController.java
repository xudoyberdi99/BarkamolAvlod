package com.company.controller;

import com.company.entity.Attachment;
import com.company.payload.ApiResponse;
import com.company.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;


    @PostMapping("/addAttachment")
    public ResponseEntity<?> addAttach(MultipartHttpServletRequest request) throws IOException {
        ApiResponse apiResponse= attachmentService.addAttachment(request);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);

    }

    @GetMapping("/getAllAttachment")
    public ResponseEntity<Page<Attachment>> findAllFilesWithPageable(int page, int size){
        Page<Attachment> attachments=attachmentService.findAllWithPage(page, size);
        return ResponseEntity.ok(attachments);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllFiles(){
        List<Attachment> attachments=attachmentService.findAllFiles();
        return ResponseEntity.ok(attachments);
    }
    @GetMapping("/user/getAttachment/{id}")
    public  ResponseEntity<?> downloadToServer(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Attachment attachment= attachmentService.downloadToServer(id, response);
        return ResponseEntity.ok(attachment);
    }
    @GetMapping("/user/view/{id}")
    public  ResponseEntity<InputStreamResource> viewFile(@PathVariable Long id, HttpServletResponse httpServletResponse) throws IOException {
        return attachmentService.getFile(id, httpServletResponse);
    }

    @DeleteMapping("/deleteAttachment/{id}")
    public ResponseEntity<?> fileDelete(@PathVariable Long id){
        ApiResponse apiResponse=attachmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
