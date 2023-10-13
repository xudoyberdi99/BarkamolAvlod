package com.company.service;

import com.company.entity.Achievements;
import com.company.entity.News;
import com.company.payload.AchievementsDto;
import com.company.payload.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AchievementsService {
    ApiResponse addAchievements(AchievementsDto achievementsDto);

    ApiResponse editAchievements(Long id, AchievementsDto achievementsDto);

    ApiResponse deleteAchievements(Long id);

    List<Achievements> getAllAchievements();

    Page<Achievements> getAllAchievementsWithPage(int page, int size);

    Achievements AchievementsById(Long id);
}
