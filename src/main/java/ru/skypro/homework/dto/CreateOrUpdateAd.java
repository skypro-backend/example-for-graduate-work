package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {

    private String title;
    private String description;
    private Integer price;
}
