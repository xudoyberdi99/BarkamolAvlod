package com.company.controller;

import com.company.entity.Admin;
import com.company.payload.ApiResponse;
import com.company.payload.LoginDto;
import com.company.security.JwtProvider;
import com.company.service.serviceImpl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        Admin admin = (Admin) authentication.getPrincipal();
        String token = jwtProvider.generateToken(admin.getUsername());
        ApiResponse user=new ApiResponse("success",true,token);
        return ResponseEntity.status(user.isSucces()?200:409).body(user);
//        return  ResponseEntity.ok(token);
    }

}
