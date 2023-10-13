package com.company.service;

import com.company.entity.Attachment;
import com.company.payload.ApiResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    ApiResponse addAttachment(MultipartHttpServletRequest request) throws IOException;

    Page<Attachment> findAllWithPage(int page, int size);

    List<Attachment> findAllFiles();

    Attachment downloadToServer(Long id, HttpServletResponse response) throws IOException;

    ApiResponse delete(Long id);

    ResponseEntity<InputStreamResource> getFile(Long id, HttpServletResponse httpServletResponse) throws FileNotFoundException;
}
