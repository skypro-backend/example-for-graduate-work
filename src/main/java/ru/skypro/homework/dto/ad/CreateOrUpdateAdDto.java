package ru.skypro.homework.dto.ad;

import lombok.Data;


@Data
public class CreateOrUpdateAdDto {
    private String description;
    private String title;
    private Integer price;
}
