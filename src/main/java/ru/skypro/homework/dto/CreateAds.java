package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAds {
    private String image;
    private String description;
    private String title;
    private Integer price;
    private Integer id; //pk
}
