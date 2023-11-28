package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.models.Ad;

public class AdMapper {

    public static Ad toAd(AdDto adDto) {
        if (adDto == null) {
            throw new NullPointerException("Tried to map null to Ad");
        }


        Ad ad = new Ad();

        ad.setAuthorId(adDto.getAuthorId());
        ad.setImage(adDto.getImage());
        ad.setPkId(adDto.getPkId());
        ad.setPrice(adDto.getPrice());
        ad.setTitle(adDto.getTitle());

        return ad;

    }

    public static AdDto fromAd(Ad ad){
        if(ad == null){
            throw new NullPointerException("Tried to map null to AdDto");
        }

        AdDto adDto = new AdDto();

        adDto.setAuthorId(ad.getAuthorId());
        adDto.setImage(ad.getImage());
        adDto.setPkId(ad.getPkId());
        adDto.setPrice(ad.getPrice());
        adDto.setTitle(ad.getTitle());

        return adDto;
    }



}
