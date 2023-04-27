package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.AdsModel;

@Data
public class FullAds {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;

    public static FullAds fromModel( AdsModel model ) {
        FullAds fullAds = new FullAds();
        fullAds.setPk(model.getId());
        fullAds.setAuthorFirstName(model.getUser().getFirstName());
        fullAds.setAuthorLastName(model.getUser().getLastName());
        fullAds.setDescription(model.getDescription());
        fullAds.setEmail(model.getUser().getUsername());
        fullAds.setImage("/ads/avatar/" + model.getId().toString());
        fullAds.setPhone(model.getUser().getPhone());
        fullAds.setPrice(model.getPrice());
        fullAds.setTitle(model.getTitle());
        return fullAds;
    }
}
