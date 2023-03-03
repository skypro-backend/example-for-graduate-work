package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private Integer id;
    private String image;
    private Integer author;
    private Integer price;
    private String title;
}
