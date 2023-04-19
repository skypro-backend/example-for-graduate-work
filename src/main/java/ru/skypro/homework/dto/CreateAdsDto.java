package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAdsDto {
    private String title;
    private String description;
    private int price;
}
