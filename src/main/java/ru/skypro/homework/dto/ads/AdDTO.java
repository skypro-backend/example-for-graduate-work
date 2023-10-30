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
public class AdDTO {
    private int pk;
    private int author;
    private String title;
    private int price;
    private String image;


    public static AdDTO fromAd(Ad ad){
        AdDTO adDTO = new AdDTO();
        adDTO.setPk(ad.getPk());
        adDTO.setAuthor(ad.getUser().getId());
        adDTO.setTitle(ad.getTitle());
        adDTO.setPrice(ad.getPrice());
        Optional.ofNullable(ad.getImage()).ifPresent(image -> adDTO.setImage(
                "/ads/" + ad.getImage().getId() + "/image"));
        return adDTO;
    }

//    public Ad toAd(Users user){
//        Ad ad = new Ad();
//        ad.setPk(this.getPk());
//        ad.setUser(user);
//        ad.setTitle(this.getTitle());
//        ad.setPrice(this.getPrice());
//        ad.setImage(this.getImage());
//        return ad;
//    }
}