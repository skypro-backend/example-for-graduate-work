package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String login;
    private String image;
    private String phone;
    private int price;
    private String title;
}
