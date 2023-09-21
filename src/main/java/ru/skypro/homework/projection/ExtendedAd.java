package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Data
public class ExtendedAd {
    private final Integer pk;
    private final String authorFirstName;
    private final String authorLastName;
    private final String description;
    private final String email;
    private final String image;
    private final String phone;
    private final Integer price;
    private final String title;

}