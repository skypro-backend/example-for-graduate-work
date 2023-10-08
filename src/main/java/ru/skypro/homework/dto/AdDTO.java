package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdDTO {
    int author;
    int pk;
    int price;
    String title;
    //String image;
}
