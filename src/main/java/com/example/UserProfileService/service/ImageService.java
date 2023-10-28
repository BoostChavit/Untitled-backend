package com.example.UserProfileService.service;

import com.example.UserProfileService.entity.Image;
import com.example.UserProfileService.entity.User;
import com.example.UserProfileService.repository.ImageRepository;
import com.example.UserProfileService.repository.ProfileRepository;
import com.example.UserProfileService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("${image.upload.directory}")
    private String imageDirectory;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity uploadImage(MultipartFile file, Integer id) {
        try {

            String filename = id + file.getOriginalFilename();
            // Construct the file path for saving
            Path filePath = Paths.get(imageDirectory, filename);

            // Save the file to the specified path
            Files.write(filePath, file.getBytes());

            User user = userRepository.findById(id);

            Image image = new Image();
            image.setName(filename);
            image.setProfile(user.getProfile());
            imageRepository.save(image);

            // Return a success response
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }
}
