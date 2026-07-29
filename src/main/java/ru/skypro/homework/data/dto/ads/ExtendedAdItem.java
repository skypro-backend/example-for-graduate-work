package ru.skypro.homework.data.dto.ads;

import lombok.Data;

@Data
public class ExtendedAdItem {
    private Long pk;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String description;
    private String image;
    private String phone;
    private String email;
}
