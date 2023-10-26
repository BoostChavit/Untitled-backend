package com.example.UserProfileService.model;

import com.example.UserProfileService.entity.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("email")
    String email;
}
