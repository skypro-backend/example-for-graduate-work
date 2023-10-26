package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDTO {
    private long pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private long price;
    private String title;
}
