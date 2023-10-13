package com.company.controller;

import com.company.entity.Achievements;
import com.company.entity.News;
import com.company.payload.AchievementsDto;
import com.company.payload.ApiResponse;
import com.company.payload.NewsDto;
import com.company.service.AchievementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api")
public class AchievementsController {
    @Autowired
    private AchievementsService achievementsService;


    @PostMapping("/achievements/add")
    public HttpEntity<?> addAchievements(@Valid @RequestBody AchievementsDto achievementsDto){
        ApiResponse apiResponse=achievementsService.addAchievements(achievementsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/achievements/edit/{id}")
    public HttpEntity<?> editAchievements(@Valid @PathVariable Long id, @RequestBody AchievementsDto achievementsDto){
        ApiResponse apiResponse=achievementsService.editAchievements(id,achievementsDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/achievements/delete/{id}")
    public HttpEntity<?> deleteAchievements(@PathVariable Long id){
        ApiResponse apiResponse=achievementsService.deleteAchievements(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/achievements/all")
    public HttpEntity<?> getallAchievements(){
        List<Achievements> achievements=achievementsService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }
    @GetMapping("/user/achievements/allWithPage")
    public HttpEntity<?> getallAchievementsWithPage(int page, int size){
        Page<Achievements> achievementsPage=achievementsService.getAllAchievementsWithPage(page,size);
        return ResponseEntity.ok(achievementsPage);
    }
    @GetMapping("/user/achievements/AchievementsById/{id}")
    public HttpEntity<?> NewsById(@PathVariable Long id){
        Achievements achievements=achievementsService.AchievementsById(id);
        return ResponseEntity.ok(achievements);
    }
}
