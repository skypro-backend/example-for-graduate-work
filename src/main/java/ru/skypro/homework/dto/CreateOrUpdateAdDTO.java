package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAdDTO {
    private String description;
    private Long price;
    private String title;
}
