package com.company.service.serviceImpl;

import com.company.entity.Connect;
import com.company.payload.ApiResponse;
import com.company.payload.ConnectDto;
import com.company.repository.ConnectRepository;
import com.company.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectServiceImpl implements ConnectService {
    @Autowired
    private ConnectRepository connectRepository;

    @Override
    public ApiResponse addConnect(ConnectDto connectDto) {
        Boolean exists = connectRepository.existsByPhoneNumber(connectDto.getPhoneNumber());
        if (exists){
            return new ApiResponse("already exists phoneNumber",false);
        }
        Connect connect=new Connect();
        connect.setPhoneNumber(connectDto.getPhoneNumber());
        connect.setEmail(connectDto.getEmail());
        connect.setAddress(connectDto.getAddress());
        connect.setTelegramlink(connectDto.getTelegramLink());
        connect.setFacebooklink(connectDto.getFacebook());
        connect.setInstagramlink(connectDto.getInstagramlink());

        connectRepository.save(connect);

        return new ApiResponse("add connection success",true);
    }

    @Override
    public ApiResponse editConnect(Long id, ConnectDto connectDto) {
        boolean exists = connectRepository.existsByPhoneNumberAndNotId(connectDto.getPhoneNumber(), id);
        if (exists){
            return new ApiResponse("already exists phoneNumber",false);
        }
        Optional<Connect> optionalConnect = connectRepository.findById(id);
        if (!optionalConnect.isPresent()){
            return new ApiResponse("not found connect", false);
        }
        Connect connect = optionalConnect.get();
        connect.setAddress(connectDto.getAddress());
        connect.setPhoneNumber(connectDto.getPhoneNumber());
        connect.setEmail(connectDto.getEmail());
        connect.setTelegramlink(connectDto.getTelegramLink());
        connect.setFacebooklink(connectDto.getFacebook());
        connect.setInstagramlink(connectDto.getInstagramlink());
        connectRepository.save(connect);

        return new ApiResponse("edit connect success",true);
    }

    @Override
    public ApiResponse deleteConnect(Long id) {
        try{
            connectRepository.deleteById(id);
            return new ApiResponse("delete connect",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Connect> getAllConnect() {
        return connectRepository.findAll();
    }

    @Override
    public Page<Connect> getAllWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return connectRepository.findAll(pageable);
    }

    @Override
    public Connect ConnectById(Long id) {
        return connectRepository.findById(id).orElse(new Connect());
    }
}
