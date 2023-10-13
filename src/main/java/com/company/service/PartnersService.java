package com.company.service;

import com.company.entity.Partners;
import com.company.payload.ApiResponse;
import com.company.payload.PartnerDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PartnersService {
    ApiResponse addPartner(PartnerDto partnerDto);

    ApiResponse editPartner(Long id, PartnerDto partnerDto);

    ApiResponse deletePartner(Long id);

    List<Partners> getAllPartner();

    Page<Partners> getAllWithPage(int page, int size);

    Partners PartnerById(Long id);
}
