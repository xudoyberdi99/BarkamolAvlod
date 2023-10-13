package com.company.service;

import com.company.entity.Connect;
import com.company.payload.ApiResponse;
import com.company.payload.ConnectDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConnectService {
    ApiResponse addConnect(ConnectDto connectDto);

    ApiResponse editConnect(Long id, ConnectDto connectDto);

    ApiResponse deleteConnect(Long id);

    List<Connect> getAllConnect();

    Page<Connect> getAllWithPage(int page, int size);

    Connect ConnectById(Long id);
}
