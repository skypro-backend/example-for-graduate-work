package ru.skypro.homework.dto.model_dto;

import lombok.Data;

@Data
public class CreateOrUpdateAdDto {

    private String title;
    private Integer price;
    private String description;
}
