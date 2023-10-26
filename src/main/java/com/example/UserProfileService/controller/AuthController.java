package com.example.UserProfileService.controller;

import com.example.UserProfileService.entity.User;
import com.example.UserProfileService.model.AuthRequest;
import com.example.UserProfileService.model.AuthResponse;
import com.example.UserProfileService.model.MeResponse;
import com.example.UserProfileService.model.RegisterRequest;
import com.example.UserProfileService.service.AuthService;
import com.example.UserProfileService.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        if(service.emailIsExisted(request)) {
            return ResponseEntity.badRequest().body("Email is used");
        }
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/me")
    public ResponseEntity<Object> me(
            HttpServletRequest request
    ) {
        MeResponse user = service.me(request);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthenticated");
    }

}
