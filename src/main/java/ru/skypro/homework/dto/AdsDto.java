package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDto {
    private int pk;
    private String title;
    private int price;
    private String image;
    private int author;
}
