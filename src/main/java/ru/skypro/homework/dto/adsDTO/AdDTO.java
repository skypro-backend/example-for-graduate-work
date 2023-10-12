package ru.skypro.homework.dto.adsDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AdDTO {
    int author;
    String image;
    int pk;
    int price;
    String title;
}
