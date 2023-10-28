package com.example.UserProfileService.controller;

import com.example.UserProfileService.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @Value("${image.upload.directory}")
    private String imageDirectory;

    @Autowired
    private ImageService imageService;

    @PostMapping("/images")
    public ResponseEntity<Object> getImage(@RequestParam("file")MultipartFile file, @RequestParam("id")Integer id ) throws IOException {
        return imageService.uploadImage(file, id);
    }
}

