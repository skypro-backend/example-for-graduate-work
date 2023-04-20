package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class FullAdsDTO {
    // id объявления
    private Long pk;
    // Имя автора объявления
    private String authorFirstName;
    // Фамилия автора объявления
    private String authorLastName;
    // Описание объявления
    private String description;
    // Логин автора объявления
    private String email;
    // Ссылка на картинку объявления
    private String image;
    // Телефон автора объявления
    private String phone;
    // Цена объявления
    private Integer price;
    // Заголовок объявления
    private String title;

    public static FullAdsDTO fromAd(Ad ad) {
        FullAdsDTO dto = new FullAdsDTO();
        dto.setPk(ad.getId());
        dto.setAuthorFirstName(ad.getAuthor().getFirstName());
        dto.setAuthorLastName(ad.getAuthor().getLastName());
        dto.setDescription(ad.getDescription());
        dto.setEmail(ad.getAuthor().getEmail());
        dto.setImage(ad.getImage());
        dto.setPhone(ad.getAuthor().getPhone());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());
        return dto;
    }
}
