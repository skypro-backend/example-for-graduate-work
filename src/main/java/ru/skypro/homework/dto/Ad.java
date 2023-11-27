package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    private long author;
    private String image;
    private String title;
    private int pk;
    private int price;

    public Ad(int pk, long author, String image, int price, String title, Class<? extends Ad> aClass) {
    }
}
