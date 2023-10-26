package com.example.UserProfileService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String gender;
    private String birthday;
    private String detail;
//    @Lob
//    @CollectionTable(name = "profile_image")
//    private List<byte[]> profileImage = new ArrayList<>();


    // Getter for profileImage
//    public List<byte[]> getProfileImage() {
//        return profileImage;
//    }


    @OneToOne(mappedBy = "profile")
    private User user;

}
