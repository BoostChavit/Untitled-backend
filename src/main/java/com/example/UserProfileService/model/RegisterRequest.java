package com.example.UserProfileService.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String birthday;
    private String detail;
    private String gender;
}
