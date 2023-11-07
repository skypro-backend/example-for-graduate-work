package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {
    private int pk;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;
}