package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    Integer authorId;
    String linkToTheImage;
    int adId;
    int adPrice;
    String adTitle;
}
