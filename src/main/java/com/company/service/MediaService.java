package com.company.service;

import com.company.entity.Media;
import com.company.payload.ApiResponse;
import com.company.payload.MediaDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MediaService {


    ApiResponse addMedia(MediaDto mediaDto);

    ApiResponse editMedia(Long id, MediaDto mediaDto);

    ApiResponse deleteMedia(Long id);

    List<Media> getAllMedia();

    Page<Media> getAllMediaWithPage(int page, int size);

    Media MediaById(Long id);
}
