package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
