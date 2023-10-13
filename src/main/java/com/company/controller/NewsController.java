package com.company.controller;

import com.company.entity.News;
import com.company.payload.ApiResponse;
import com.company.payload.NewsDto;
import com.company.service.NewsService;
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
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("/news/add")
    public HttpEntity<?> addNews(@Valid @RequestBody NewsDto newsDto){
        ApiResponse apiResponse=newsService.addNews(newsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/news/editNews/{id}")
    public HttpEntity<?> editNews(@Valid @PathVariable Long id, @RequestBody NewsDto newsDto){
        ApiResponse apiResponse=newsService.editNews(id,newsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/news/deleteNews/{id}")
    public HttpEntity<?> deleteNews(@PathVariable Long id){
        ApiResponse apiResponse=newsService.deleteNews(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/news/allNews")
    public HttpEntity<?> getallNews(){
        List<News> newsList=newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }
    @GetMapping("/user/news/allNewsWithPage")
    public HttpEntity<?> getallNewsWithPage(int page, int size){
        Page<News> newsList=newsService.getAllNewsWithPage(page,size);
        return ResponseEntity.ok(newsList);
    }
    @GetMapping("/user/news/NewsById/{id}")
    public HttpEntity<?> NewsById(@PathVariable Long id){
       News news=newsService.NewsById(id);
       return ResponseEntity.ok(news);
    }
}
