package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDto {

    private int id;
    private int authorId;
    private String image;
    private int price;
    private String title;

}
