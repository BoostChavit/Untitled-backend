package com.example.UserProfileService.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
