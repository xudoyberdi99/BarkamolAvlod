package com.company.service.serviceImpl;

import com.company.entity.BAMS;
import com.company.payload.ApiResponse;
import com.company.payload.BAMDto;
import com.company.repository.BAMSRepository;
import com.company.service.BAMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BAMSServiceImpl implements BAMSService {
    @Autowired
    private BAMSRepository bamsRepository;

    @Override
    public ApiResponse addBAMS(BAMDto bamDto) {
        Boolean exists = bamsRepository.existsByPhoneNumber(bamDto.getPhoneNumber());
        if(exists){
            return new ApiResponse("already exists BAMS", false);
        }
        BAMS bams=new BAMS();
        bams.setTitle(bamDto.getTitle());
        bams.setFullAddress(bamDto.getFullAddress());
        bams.setPhoneNumber(bamDto.getPhoneNumber());
        bams.setDirektorName(bamDto.getDirektorName());

        bamsRepository.save(bams);
        return new ApiResponse("add bams success",true);
    }

    @Override
    public ApiResponse editBAMS(Long id, BAMDto bamDto) {
        boolean exists = bamsRepository.existsByPhoneNumberAndnotId(bamDto.getPhoneNumber(), id);
        if (exists){
            return new ApiResponse("already exists phoneNumber",false);
        }

        Optional<BAMS> bamsOptional = bamsRepository.findById(id);
        if (!bamsOptional.isPresent()){
            return new ApiResponse("not found bams", false);
        }


        BAMS bams = bamsOptional.get();
        bams.setTitle(bamDto.getTitle());
        bams.setDirektorName(bamDto.getDirektorName());
        bams.setFullAddress(bamDto.getFullAddress());
        bams.setPhoneNumber(bamDto.getPhoneNumber());

        bamsRepository.save(bams);

        return new ApiResponse("edit bams success",true);
    }

    @Override
    public ApiResponse deleteBAMS(Long id) {
        try{
            bamsRepository.deleteById(id);
            return new ApiResponse("delete Bams",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<BAMS> getAllBAMS() {
        return bamsRepository.findAll();
    }

    @Override
    public Page<BAMS> getAllWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return bamsRepository.findAll(pageable);
    }

    @Override
    public BAMS BAMSById(Long id) {
        return bamsRepository.findById(id).orElse(new BAMS());
    }
}
