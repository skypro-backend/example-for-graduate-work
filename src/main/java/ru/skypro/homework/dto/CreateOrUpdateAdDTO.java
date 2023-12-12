package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAdDTO {
    private String title;
    private Integer price;
    private String description;
}
