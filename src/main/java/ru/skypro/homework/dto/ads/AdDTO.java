package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Data
public class AdDTO {
    private int id;
    private User author;
    private Image image;
    private Integer pk;
    private int price;
    private String title;
    private String description;


    public static AdDTO fromAd(Ad ad) {

        AdDTO dto = new AdDTO();
        dto.setId(ad.getId());
        dto.setAuthor(ad.getAuthor());
        dto.setPk(ad.getPk());
        dto.setTitle(ad.getTitle());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImage());
        dto.setDescription(ad.getDescription());

        return dto;
    }

    public Ad toAd() {

        Ad ad = new Ad();

        ad.setId(this.getId());
        ad.setAuthor(this.getAuthor());
        ad.setTitle(this.getTitle());
        ad.setPrice(this.getPrice());
        ad.setPk(this.getPk());
        ad.setImage(this.getImage());
        ad.setDescription(this.getDescription());

        return ad;
    }

}
