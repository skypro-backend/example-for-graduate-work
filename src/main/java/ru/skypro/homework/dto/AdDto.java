package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class AdDto {

    private Integer user; //($int32) id автора объявления
    private String image; // ссылка на картинку объявления
    @JsonProperty("pk")
    private Integer pk; //($int32)id объявления
    private Integer price; //($int32) цена объявления
    private String title; //заголовок объявления

    public static AdDto fromAd(Ad ad) {
        AdDto dto = new AdDto();
        dto.setPk(ad.getPk());
        dto.setUser(ad.getUser().getId());
        if (ad.getImage() != null) {
            dto.setImage(String.format("/ads/image/%s", ad.getImage()));
        } else {
            dto.setImage(null);
        }
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

