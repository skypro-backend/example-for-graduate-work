package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class FullAdsDto {
    private int pk;
    private String title;
    private String description;
    private int price;
    private String[] image;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String phone;
}
