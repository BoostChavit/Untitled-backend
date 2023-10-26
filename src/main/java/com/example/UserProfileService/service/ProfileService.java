package com.example.UserProfileService.service;
import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.entity.User;
import com.example.UserProfileService.model.ProfileRequest;
import com.example.UserProfileService.model.ProfileResponse;
import com.example.UserProfileService.repository.ProfileRepository;
import com.example.UserProfileService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class ProfileService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getPersonById(UUID id) {
        return profileRepository.findById(id);
    }

    public ProfileResponse myProfile(ProfileRequest request) {

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
//        System.out.println(user.getProfile());
        return ProfileResponse.builder().build();
    }
}
