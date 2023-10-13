package com.company.service.serviceImpl;

import com.company.entity.Achievements;
import com.company.entity.Attachment;
import com.company.entity.template.AbstractEntity;
import com.company.payload.AchievementsDto;
import com.company.payload.ApiResponse;
import com.company.repository.AchievementsRepository;
import com.company.repository.AttachmentRepository;
import com.company.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AchievementsServiceImpl implements AchievementsService {

    @Autowired
    private AchievementsRepository achievementsRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public ApiResponse addAchievements(AchievementsDto achievementsDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(achievementsDto.getImagesId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found image",false);
        }

        Achievements achievements=new Achievements();
        achievements.setBody(achievementsDto.getBody());
        achievements.setName(achievementsDto.getName());
        achievements.setDescription(achievementsDto.getDescription());
        achievements.setImages(optionalAttachment.get());
        achievementsRepository.save(achievements);
        return new ApiResponse("add achievements success", true);
    }

    @Override
    public ApiResponse editAchievements(Long id, AchievementsDto achievementsDto) {
        Optional<Achievements> optionalAchievements = achievementsRepository.findById(id);
        if (!optionalAchievements.isPresent()){
            return new ApiResponse("not found",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(achievementsDto.getImagesId());
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("not found image",false);
        }
        Achievements achievements = optionalAchievements.get();
        achievements.setName(achievementsDto.getName());
        achievements.setBody(achievementsDto.getBody());
        achievements.setDescription(achievementsDto.getDescription());
        achievements.setImages(optionalAttachment.get());

        achievementsRepository.save(achievements);
        return new ApiResponse("edit success",true);
    }

    @Override
    public ApiResponse deleteAchievements(Long id) {
        try{
            achievementsRepository.deleteById(id);
            return new ApiResponse("delete Achievements",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    @Override
    public List<Achievements> getAllAchievements() {
        return achievementsRepository.findAll();
    }

    @Override
    public Page<Achievements> getAllAchievementsWithPage(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return achievementsRepository.findAll(pageable);
    }

    @Override
    public Achievements AchievementsById(Long id) {
        return achievementsRepository.findById(id).orElse(new Achievements());
    }
}
