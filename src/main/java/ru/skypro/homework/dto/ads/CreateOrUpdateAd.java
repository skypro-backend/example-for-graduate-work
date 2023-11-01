package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
public class CreateOrUpdateAd {


    @Size(min = 4, max = 32)
    private String title;

    private int price;

    @Size(min = 8, max = 64)
    private String description;

    public Ad toAd(Ad ad) {

        if  (ad == null) {
            ad = new Ad();
        } else {
            ad.setPrice(this.getPrice());
            ad.setDescription(this.getDescription());
            ad.setTitle(this.getTitle());
        }

        return ad;
    }

    public static CreateOrUpdateAd fromAd(Ad ad) {

        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();
        createOrUpdateAd.setTitle(ad.getTitle());
        createOrUpdateAd.setPrice(ad.getPrice());
        createOrUpdateAd.setDescription(ad.getDescription());

        return createOrUpdateAd;
    }


}
