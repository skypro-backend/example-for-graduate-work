package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class CreateOrUpdateAdDto {

    private String title;
    private int price;
    private String description;
}
