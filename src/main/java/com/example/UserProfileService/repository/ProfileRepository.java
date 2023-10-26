package com.example.UserProfileService.repository;

import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findByid(UUID id);
}
