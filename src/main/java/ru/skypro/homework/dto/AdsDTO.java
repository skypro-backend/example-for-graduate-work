package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class AdsDTO {
    // id автора объявления
    private Long author;
    // Ссылка на картинку объявления
    private String image;
    // id объявления
    private Long pk;
    // Цена объявления
    private Integer price;
    // Заголовок объявления
    private String title;

    public static AdsDTO fromAd(Ad ad) {
        AdsDTO dto = new AdsDTO();
        dto.setTitle(ad.getTitle());
        dto.setAuthor(ad.getAuthor().getId());
        dto.setPk(ad.getId());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImage());
        return dto;
    }
}
