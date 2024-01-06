package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewAdDto {
    CreateOrUpdateAdDto properties;
    String image;
}
