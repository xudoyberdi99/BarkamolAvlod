package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.template.AbstractEntity;
import com.company.payload.ApiResponse;
import com.company.repository.AttachmentRepository;
import com.company.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    private static final  String upload="Upload";

    @Override
    public ApiResponse addAttachment(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            if (file!=null){
                String originalFilename = file.getOriginalFilename();
                long size = file.getSize();
                String contentType = file.getContentType();
                Attachment attachment=new Attachment();
                attachment.setFileName(originalFilename);
                attachment.setSize(size);
                attachment.setContentType(contentType);

                String[] split = originalFilename.split("\\.");
                String fileSystemName = UUID.randomUUID().toString()+"."+split[split.length-1];
                attachment.setSystemName(fileSystemName);
                attachmentRepository.save(attachment);
                Path path= Paths.get(upload+"/"+fileSystemName);
                Files.copy(file.getInputStream(),path);
                return new ApiResponse("fayl saqlandi",true);
            }
        }
        return new ApiResponse("Fayl saqlanmadi",false);
    }

    @Override
    public Page<Attachment> findAllWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return attachmentRepository.findAll(pageable);
    }

    @Override
    public List<Attachment> findAllFiles() {
        return attachmentRepository.findAll();
    }

    @Override
    public ApiResponse delete(Long id) {
        try{
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
            if (!optionalAttachment.isPresent()){
                return new ApiResponse("not found file", false);
            }
            Attachment attachment = optionalAttachment.get();
            File file = new File(upload+"/"+attachment.getSystemName());
            if (file.delete()) {
                attachmentRepository.deleteById(id);
                return new ApiResponse("delete attachment", true);
            }else {
                return new ApiResponse("Error",false);
            }
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public Attachment downloadToServer(Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition","attachment; file\"" + attachment.getFileName()+ "\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream=new FileInputStream(upload+"/"+attachment.getSystemName());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());

            return attachment;
        }
        return new Attachment();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseEntity<InputStreamResource> getFile(Long id, HttpServletResponse httpServletResponse) throws FileNotFoundException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()){
            return null;
        }
        Attachment attachment = optionalAttachment.get();
        Path path = Paths.get(this.upload);
        File file = new File(path.toFile()+"/"+attachment.getSystemName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getFileName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .body(resource);
//        File file1=new File()

    }
}
