package com.company.controller;

import com.company.entity.News;
import com.company.entity.Partners;
import com.company.payload.ApiResponse;
import com.company.payload.NewsDto;
import com.company.payload.PartnerDto;
import com.company.service.PartnersService;
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
public class PartnersController {
    @Autowired
    private PartnersService partnersService;

    @PostMapping("/partners/add")
    public HttpEntity<?> addPartner(@Valid @RequestBody PartnerDto partnerDto){
        ApiResponse apiResponse=partnersService.addPartner(partnerDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/partners/edit/{id}")
    public HttpEntity<?> editPartner(@Valid @PathVariable Long id, @RequestBody PartnerDto partnerDto){
        ApiResponse apiResponse=partnersService.editPartner(id,partnerDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/partners/delete/{id}")
    public HttpEntity<?> deletePartner(@PathVariable Long id){
        ApiResponse apiResponse=partnersService.deletePartner(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/partners/all")
    public HttpEntity<?> getallPartner(){
        List<Partners> partners=partnersService.getAllPartner();
        return ResponseEntity.ok(partners);
    }
    @GetMapping("/user/partners/allWithPage")
    public HttpEntity<?> getallWithPage(int page, int size){
        Page<Partners> partners=partnersService.getAllWithPage(page,size);
        return ResponseEntity.ok(partners);
    }
    @GetMapping("/user/partners/PartnerById/{id}")
    public HttpEntity<?> PartnerById(@PathVariable Long id){
        Partners partners=partnersService.PartnerById(id);
        return ResponseEntity.ok(partners);
    }
}
