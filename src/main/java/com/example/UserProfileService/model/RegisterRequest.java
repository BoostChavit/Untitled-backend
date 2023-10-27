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

    public boolean isComplete() {
        if(email.isEmpty() && password.isEmpty() && name.isEmpty() && birthday.isEmpty() && detail.isEmpty() && gender.isEmpty()) {
            return false;
        }
        return true;
    }
}
