package com.example.UserProfileService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    private Profile profile;
}
