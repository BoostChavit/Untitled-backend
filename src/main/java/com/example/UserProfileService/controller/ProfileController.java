package com.example.UserProfileService.controller;
// PersonController.java (Controller)

import com.example.UserProfileService.model.ProfileRequest;
import com.example.UserProfileService.model.ProfileResponse;
import com.example.UserProfileService.model.RegisterRequest;
import com.example.UserProfileService.repository.UserRepository;
import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import com.example.UserProfileService.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile")
    public ResponseEntity<Object> profile(
            @RequestBody ProfileRequest request
    ) {
        return ResponseEntity.ok(profileService.myProfile(request));
    }

    // Add more request mappings for CRUD operations




}

