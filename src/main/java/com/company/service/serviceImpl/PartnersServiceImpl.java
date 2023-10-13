package com.company.service.serviceImpl;

import com.company.entity.Attachment;
import com.company.entity.Partners;
import com.company.payload.ApiResponse;
import com.company.payload.PartnerDto;
import com.company.repository.AttachmentRepository;
import com.company.repository.PartnersRepository;
import com.company.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnersServiceImpl implements PartnersService {
    @Autowired
    private PartnersRepository partnersRepository;
    @Autowired
    private  AttachmentRepository attachmentRepository;
    @Override
    public ApiResponse addPartner(PartnerDto partnerDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(partnerDto.getImageId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found image", false);
        }
        Partners partners=new Partners();
        partners.setName(partnerDto.getName());
        partners.setImage(optionalAttachment.get());
        partnersRepository.save(partners);
        return new ApiResponse("add partners success",true);
    }

    @Override
    public ApiResponse editPartner(Long id, PartnerDto partnerDto) {
        Optional<Partners> optionalPartners = partnersRepository.findById(id);
        if (!optionalPartners.isPresent()){
            return new ApiResponse("not found partners",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(partnerDto.getImageId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found image",false);
        }
        Partners partners = optionalPartners.get();
        partners.setName(partnerDto.getName());
        partners.setImage(optionalAttachment.get());

        partnersRepository.save(partners);
        return new ApiResponse("edit partners success", true);
    }

    @Override
    public ApiResponse deletePartner(Long id) {
        try{
            partnersRepository.deleteById(id);
            return new ApiResponse("delete partners",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Partners> getAllPartner() {
        return partnersRepository.findAll();
    }

    @Override
    public Page<Partners> getAllWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return partnersRepository.findAll(pageable);
    }

    @Override
    public Partners PartnerById(Long id) {
        return partnersRepository.findById(id).orElse(new Partners());
    }
}
