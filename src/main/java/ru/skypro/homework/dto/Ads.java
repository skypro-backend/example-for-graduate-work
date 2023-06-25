package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private int authorId;
    private String image;
    private int pk;
    private int price;
    private String title;
}
