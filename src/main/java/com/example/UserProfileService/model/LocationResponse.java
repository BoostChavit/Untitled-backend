package com.example.UserProfileService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {
    private String ipAddress;
    private String city;
    private String country;
    private String latitude;
    private String longitude;
}
