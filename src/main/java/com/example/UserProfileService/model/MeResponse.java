package com.example.UserProfileService.model;

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
    @JsonProperty("username")
    String username;
    @JsonProperty("email")
    String email;
}
