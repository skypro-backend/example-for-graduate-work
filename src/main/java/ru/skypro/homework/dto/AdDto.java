package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entities.UserEntity;

@Data
public class AdDto {

    private int id;
    private UserEntity author;
    private String title;
    private String description;
    private int price;
    private String image;
}