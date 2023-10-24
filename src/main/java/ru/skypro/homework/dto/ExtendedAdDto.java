package ru.skypro.homework.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExtendedAdDto {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}
