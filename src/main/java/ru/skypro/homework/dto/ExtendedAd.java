package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {

    Integer pk;
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String image;
    String phone;
    Integer price;
    String title;
}
