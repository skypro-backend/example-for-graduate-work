package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.AdsModel;

@Data
public class Ads {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

    public static Ads fromModel( AdsModel model ) {
        Ads ads = new Ads();
        ads.setAuthor(model.getUser().getPk());
        ads.setImage("/ads/avatar/" + model.getId());
        ads.setPk(model.getId());
        ads.setPrice(model.getPrice());
        ads.setTitle(model.getTitle());
        return ads;
    }
}
