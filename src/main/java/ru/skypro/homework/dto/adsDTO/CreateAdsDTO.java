package ru.skypro.homework.dto.adsDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAdsDTO {
    String title;
    Integer price;
    String description;
}
