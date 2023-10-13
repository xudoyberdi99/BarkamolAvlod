package com.company.service;

import com.company.entity.News;
import com.company.payload.ApiResponse;
import com.company.payload.NewsDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    ApiResponse addNews(NewsDto newsDto);

    ApiResponse editNews(Long id, NewsDto newsDto);

    ApiResponse deleteNews(Long id);

    List<News> getAllNews();

    Page<News> getAllNewsWithPage(int page, int size);

    News NewsById(Long id);
}
