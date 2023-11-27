package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ads;

@Component
public class AdsMapper {
    public Ads mapToAds(Ads ads) {
        return new Ads(
                Ads.getPk(),
                Ads.getAuthor(),
                Ads.getImage(),
                Ads.getPrice(),
                Ads.getTitle());
}
    }
