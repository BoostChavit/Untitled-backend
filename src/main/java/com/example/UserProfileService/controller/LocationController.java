package com.example.UserProfileService.controller;

import com.example.UserProfileService.model.LocationResponse;
import com.example.UserProfileService.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {
    private LocationService locationService;

    @GetMapping("/location")
    public LocationResponse getLocation(
            @RequestParam(value="ipAddress") String ipAddress
    ) throws Exception {

        locationService = new LocationService();
        return locationService.getLocation(ipAddress);
    }

}
