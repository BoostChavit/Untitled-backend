package com.example.UserProfileService.model;

import jakarta.persistence.Lob;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfileRequest {
    private String email;
}
