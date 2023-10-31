package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Users;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedAd {

    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;

    public static ExtendedAd fromAd(Ad ad){
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(ad.getPk());
        extendedAd.setAuthorFirstName(ad.getUser().getFirstName());
        extendedAd.setAuthorLastName(ad.getUser().getLastName());
        extendedAd.setDescription(ad.getDescription());
        extendedAd.setEmail(ad.getUser().getUsername());
        extendedAd.setPhone(ad.getUser().getPhone());
        extendedAd.setTitle(ad.getTitle());
        extendedAd.setPrice(ad.getPrice());
        Optional.ofNullable(ad.getImage()).ifPresent(image -> extendedAd.setImage(
                "/ads/" + ad.getImage().getId() + "/image"));
        return extendedAd;
    }


}