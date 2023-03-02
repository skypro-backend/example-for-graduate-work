package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
   private Integer id; // pk
    private String image;
    private Integer author; // это author(user)_id
    private Integer price;
    private String title;
}
