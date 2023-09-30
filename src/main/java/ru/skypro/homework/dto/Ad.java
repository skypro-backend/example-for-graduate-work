package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    Integer author;
    String image;
    int id;
    int price;
    String title;
}
