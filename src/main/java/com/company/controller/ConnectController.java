package com.company.controller;

import com.company.entity.BAMS;
import com.company.entity.Connect;
import com.company.payload.ApiResponse;
import com.company.payload.BAMDto;
import com.company.payload.ConnectDto;
import com.company.service.ConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin(value = "http://localhost:5173",maxAge = 3600)
@CrossOrigin(value = "**",maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConnectController {
    @Autowired
    private ConnectService connectService;

    @PostMapping( "/connect/add")
    public HttpEntity<?> addConnect(@Valid @RequestBody ConnectDto connectDto){
        ApiResponse apiResponse=connectService.addConnect(connectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PutMapping("/connect/edit/{id}")
    public HttpEntity<?> editConnect(@Valid @PathVariable Long id, @RequestBody ConnectDto connectDto){
        ApiResponse apiResponse=connectService.editConnect(id,connectDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @DeleteMapping("/connect/delete/{id}")
    public HttpEntity<?> deleteConnect(@PathVariable Long id){
        ApiResponse apiResponse=connectService.deleteConnect(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @GetMapping("/user/connect/all")
    public HttpEntity<?> getallConnect(){
        List<Connect> connects=connectService.getAllConnect();
        return ResponseEntity.ok(connects);
    }
    @GetMapping("/user/connect/allWithPage")
    public HttpEntity<?> getallWithPage(int page, int size){
        Page<Connect> connects=connectService.getAllWithPage(page,size);
        return ResponseEntity.ok(connects);
    }
    @GetMapping("/user/connect/ConnectById/{id}")
    public HttpEntity<?> ConnectById(@PathVariable Long id){
        Connect connect=connectService.ConnectById(id);
        return ResponseEntity.ok(connect);
    }
}
