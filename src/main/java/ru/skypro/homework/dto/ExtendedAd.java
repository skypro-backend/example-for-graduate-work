package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ExtendedAd {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String  phone;
    private Integer price;
    private String  title;

}
