package ru.skypro.homework.dto.ads;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
