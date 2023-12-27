package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAdDto {
    private String title;
    private Integer price;
    private String description;

}
