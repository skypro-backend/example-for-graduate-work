package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtendedAdDTO {

    int pk;
    String description;
    String image;
    int price;
    String title;
    String authorFirstName;
    String authorLastName;
    String phone;
    String email;
}
