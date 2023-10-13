package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.Media;
import com.company.payload.ApiResponse;
import com.company.payload.MediaDto;
import com.company.repository.AttachmentRepository;
import com.company.repository.MediaRepository;
import com.company.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public ApiResponse addMedia(MediaDto mediaDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(mediaDto.getVedioId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found vedio",false);
        }
        Media media=new Media();
        media.setVedio(optionalAttachment.get());
        media.setDescription(mediaDto.getDescription());
        mediaRepository.save(media);

        return new ApiResponse("add news success",true);
    }

    @Override
    public ApiResponse editMedia(Long id, MediaDto mediaDto) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (!optionalMedia.isPresent()){
            return new ApiResponse("not found media",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(mediaDto.getVedioId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found vedio",false);
        }

        Media media = optionalMedia.get();
        media.setVedio(optionalAttachment.get());
        media.setDescription(mediaDto.getDescription());

        mediaRepository.save(media);
        return new ApiResponse("edit media success", true);
    }

    @Override
    public ApiResponse deleteMedia(Long id) {
        try{
            mediaRepository.deleteById(id);
            return new ApiResponse("delete Media",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public Page<Media> getAllMediaWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return mediaRepository.findAll(pageable);
    }

    @Override
    public Media MediaById(Long id) {
        return mediaRepository.findById(id).orElse(new Media());
    }
}
