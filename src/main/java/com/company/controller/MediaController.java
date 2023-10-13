package com.company.controller;

import com.company.entity.Media;
import com.company.entity.News;
import com.company.payload.ApiResponse;
import com.company.payload.MediaDto;
import com.company.payload.NewsDto;
import com.company.service.MediaService;
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
public class MediaController {
    @Autowired
    private MediaService mediaService;


    @PostMapping("/media/add")
    public ResponseEntity<?> addMedia(@Valid @RequestBody MediaDto mediaDto){
        ApiResponse apiResponse=mediaService.addMedia(mediaDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/media/edit/{id}")
    public HttpEntity<?> editMedia(@Valid @PathVariable Long id, @RequestBody MediaDto mediaDto){
        ApiResponse apiResponse=mediaService.editMedia(id,mediaDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/media/delete/{id}")
    public HttpEntity<?> deleteMedia(@PathVariable Long id){
        ApiResponse apiResponse=mediaService.deleteMedia(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/media/all")
    public HttpEntity<?> getallMedia(){
        List<Media> mediaList=mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
    @GetMapping("/user/media/allWithPage")
    public HttpEntity<?> getallMediaWithPage(int page, int size){
        Page<Media> mediaPage=mediaService.getAllMediaWithPage(page,size);
        return ResponseEntity.ok(mediaPage);
    }
    @GetMapping("/user/media/MediaById/{id}")
    public HttpEntity<?> MediaById(@PathVariable Long id){
        Media media=mediaService.MediaById(id);
        return ResponseEntity.ok(media);
    }

}
