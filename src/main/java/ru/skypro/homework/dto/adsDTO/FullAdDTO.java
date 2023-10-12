package ru.skypro.homework.dto.adsDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FullAdDTO {
    int pk;
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String image;
    String phone;
    int price;
    String title;
}
