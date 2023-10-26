package com.example.UserProfileService.controller;

import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController {

//    @Autowired
//    private ProfileRepository profileRepository;
//
//    @PostMapping("/uploadProfilePictures/{userId}")
//    public String uploadProfile(@PathVariable("userId") UUID userId, @RequestParam("files") MultipartFile[] files) {
//        Profile profile = profileRepository.findById(userId).orElse(null);
//        if (profile != null) {
//            List<byte[]> profileImages = (profile.getProfileImage());
//            for (MultipartFile file : files) {
//                try {
//                    profile.getProfileImage().add(file.getBytes());
//                } catch (IOException e) {
//                    // Handle file upload error
//                }
//            }
//            profileRepository.save(profile);
//        }
//        return "redirect:/profile"; // Redirect to the user's profile page
//    }
}
