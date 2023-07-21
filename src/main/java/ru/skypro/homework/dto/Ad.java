package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    Integer author;
    String image;
    Integer pk;
    Integer price;
    String title;
}
