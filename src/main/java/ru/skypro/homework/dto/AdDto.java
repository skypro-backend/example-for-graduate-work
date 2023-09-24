package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.UserRepository;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class AdDto {

    private Integer author; //($int32) id автора объявления
    private String image; // ссылка на картинку объявления
    private Integer pk; //($int32)id объявления
    private Integer price; //($int32) цена объявления
    private String title; //заголовок объявления

    public static AdDto fromAd(Ad ad) {
        AdDto dto = new AdDto();
        dto.setPk(ad.getPk());
        dto.setAuthor(ad.getAuthor().getId());
        dto.setImage(ad.getImage());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());

        return dto;
    }


    public Ad toAd() {
        Ad ad = new Ad();
        ad.setPk(this.getPk());
        ad.setImage(this.getImage());
        ad.setPrice(this.getPrice());
        ad.setTitle(this.getTitle());

        return ad;
    }
}

