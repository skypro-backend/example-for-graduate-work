package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class FullAds { //оставить
    private String image;
    private String authorLastName;
    private String authorFirstName;
    private String phone;
    private String description;
    private String title;
    private String email;
    private Integer price;
    private Integer id;
}
