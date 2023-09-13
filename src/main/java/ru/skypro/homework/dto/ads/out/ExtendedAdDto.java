package ru.skypro.homework.dto.ads.out;

import lombok.Data;

@Data
public class ExtendedAdDto {
    private Integer pk;
    private String image;
    private String authorFirstName;
    private String authorLastName;
    private String phone;
    private String email;
    private String title;
    private Integer price;
    private String description;
}
