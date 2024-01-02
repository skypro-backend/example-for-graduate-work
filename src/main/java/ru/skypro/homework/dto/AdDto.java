package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class AdDto {
    private Long author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;


    public AdDto(Ad ad) {
        this.author = ad.getAuthor();
        this.image = ad.getImage();
        this.pk = ad.getPk();
        this.price = ad.getPrice();
        this.title = ad.getTitle();
    }
}
