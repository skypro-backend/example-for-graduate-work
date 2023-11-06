package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private int author;
    private byte [] image;
    private int pk;
    private int price;
    private String title;
}