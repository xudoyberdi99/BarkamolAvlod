package com.company.controller;

import com.company.entity.BAMS;
import com.company.entity.Partners;
import com.company.payload.ApiResponse;
import com.company.payload.BAMDto;
import com.company.payload.PartnerDto;
import com.company.service.BAMSService;
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
public class BAMSController {
    @Autowired
    private BAMSService bamsService;

    @PostMapping("/bams/add")
    public HttpEntity<?> addBAMS(@Valid @RequestBody BAMDto bamDto){
        ApiResponse apiResponse=bamsService.addBAMS(bamDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/bams/edit/{id}")
    public HttpEntity<?> editBAMS(@Valid @PathVariable Long id, @RequestBody BAMDto bamDto){
        ApiResponse apiResponse=bamsService.editBAMS(id,bamDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/bams/delete/{id}")
    public HttpEntity<?> deleteBAMS(@PathVariable Long id){
        ApiResponse apiResponse=bamsService.deleteBAMS(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/bams/all")
    public HttpEntity<?> getallBAMS(){
        List<BAMS> bamsList=bamsService.getAllBAMS();
        return ResponseEntity.ok(bamsList);
    }
    @GetMapping("/user/bams/allWithPage")
    public HttpEntity<?> getallWithPage(int page, int size){
        Page<BAMS> bams=bamsService.getAllWithPage(page,size);
        return ResponseEntity.ok(bams);
    }
    @GetMapping("/user/bams/BAMSById/{id}")
    public HttpEntity<?> BAMSById(@PathVariable Long id){
        BAMS bams=bamsService.BAMSById(id);
        return ResponseEntity.ok(bams);
    }
}
