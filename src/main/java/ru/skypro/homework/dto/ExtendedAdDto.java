package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAdDto {

    private Long pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;

}
