package ru.skypro.homework.dto;

import lombok.Data;

/**
 * ExtendedAdDto is the Data Transfer Object used to get information about an ad by its ID
 * @author radyushinaalena
 */
@Data
public class ExtendedAdDto {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;
}

