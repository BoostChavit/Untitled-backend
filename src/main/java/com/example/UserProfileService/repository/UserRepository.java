package com.example.UserProfileService.repository;

import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findById(Integer id);

}
