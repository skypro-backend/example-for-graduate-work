package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class Ad {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
