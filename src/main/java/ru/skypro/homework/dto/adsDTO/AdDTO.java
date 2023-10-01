package ru.skypro.homework.dto.adsDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    int author;
    String image;
    int pk;
    int price;
    String title;
}
