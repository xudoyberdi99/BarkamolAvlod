package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.News;
import com.company.payload.ApiResponse;
import com.company.payload.NewsDto;
import com.company.repository.AttachmentRepository;
import com.company.repository.NewsRepository;
import com.company.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public ApiResponse addNews(NewsDto newsDto) {
        Optional<Attachment> attachment = attachmentRepository.findById(newsDto.getImagesId());
       if (!attachment.isPresent()){
           return  new ApiResponse("not found attachment", false);
       }

        News news=new News();
        news.setName(newsDto.getName());
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setImages(attachment.get());
        newsRepository.save(news);
        return new ApiResponse("add news success",true);
    }

    @Override
    public ApiResponse editNews(Long id, NewsDto newsDto) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (!optionalNews.isPresent()){
            return new ApiResponse("not found news",false);
        }
        Optional<Attachment> attachment = attachmentRepository.findById(newsDto.getImagesId());
        if (!attachment.isPresent()){
            return  new ApiResponse("not found attachment", false);
        }
        News news = optionalNews.get();
        news.setName(newsDto.getName());
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setImages(attachment.get());

        newsRepository.save(news);
        return new ApiResponse("news edit success",true);
    }

    @Override
    public ApiResponse deleteNews(Long id) {
        try{
            newsRepository.deleteById(id);
            return new ApiResponse("delete news",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public Page<News> getAllNewsWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return newsRepository.findAll(pageable);
    }

    @Override
    public News NewsById(Long id) {
        return newsRepository.findById(id).orElse(new News());
    }

}
