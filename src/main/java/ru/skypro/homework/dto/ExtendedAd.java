package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedAd {

    private int pk = 0;
    private String authorFirstName = "string";
    private String authorLastName = "string";
    private String description = "string";
    private String email = "string";
    private String image = "string";
    private String phone = "string";
    private int price = 0;
    private String title = "string";

}
