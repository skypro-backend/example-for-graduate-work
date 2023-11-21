package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.models.Ads;

public class AdsMapper {

    public static Ads toAds(AdsDto adsDto) {

        if (adsDto == null) {
            throw new NullPointerException("Tried to map null to Ads");

        }

        Ads ads = new Ads();

        ads.setCount(adsDto.getCount());

        return ads;
    }

    public static AdsDto fromAds(Ads ads) {

        if (ads == null) {
            throw new NullPointerException("Tried to map null to AdsDto");
        }

        AdsDto adsDto = new AdsDto();

        adsDto.setCount(ads.getCount());

        return adsDto;
    }
}
