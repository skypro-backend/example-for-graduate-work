package ru.skypro.homework.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ExtendedAd {
    private UUID id;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;
}
