package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class CreateAdsDTO {
    // Описание объявления
    private String description;
    // Цена объявления
    private Integer price;
    // Заголовок объявления
    private String title;

    public Ad toAd() {
        Ad ad = new Ad();
        // TODO ad.setAuthor();
        ad.setDescription(description);
        ad.setTitle(title);
        ad.setPrice(price);
        return ad;
    }
}
