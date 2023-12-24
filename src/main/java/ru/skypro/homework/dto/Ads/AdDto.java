package ru.skypro.homework.dto.Ads;

import lombok.Data;

@Data
public class AdDto {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
