package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAds { // оставить
    private String description;
    private String title;
    private Integer price;
}
