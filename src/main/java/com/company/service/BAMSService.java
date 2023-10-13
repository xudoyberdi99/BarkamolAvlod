package com.company.service;

import com.company.entity.BAMS;
import com.company.payload.ApiResponse;
import com.company.payload.BAMDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BAMSService {
    ApiResponse addBAMS(BAMDto bamDto);

    ApiResponse editBAMS(Long id, BAMDto bamDto);

    ApiResponse deleteBAMS(Long id);

    List<BAMS> getAllBAMS();

    Page<BAMS> getAllWithPage(int page, int size);

    BAMS BAMSById(Long id);
}
