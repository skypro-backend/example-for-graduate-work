package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {

    private String description;
    private Integer price;
    private String title;

}
