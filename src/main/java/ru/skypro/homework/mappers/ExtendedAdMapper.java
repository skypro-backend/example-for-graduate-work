package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.models.ExtendedAd;

public class ExtendedAdMapper {

    public static ExtendedAd toExtendedAd(ExtendedAdDto extendedAdDto){
        if(extendedAdDto == null){
            throw new NullPointerException("Tried to map null in ExtendedAd");
        }

        ExtendedAd extendedAd = new ExtendedAd();

        extendedAd.setPk(extendedAdDto.getPk());
        extendedAd.setAuthorFirstName(extendedAdDto.getAuthorFirstName());
        extendedAd.setAuthorLastName(extendedAdDto.getAuthorLastName());
        extendedAd.setDescription(extendedAdDto.getDescription());
        extendedAd.setEmail(extendedAdDto.getEmail());
        extendedAd.setImage(extendedAdDto.getImage());
        extendedAd.setPhone(extendedAdDto.getPhone());
        extendedAd.setPrice(extendedAdDto.getPrice());
        extendedAd.setTitle(extendedAdDto.getTitle());

        return extendedAd;
    }

    public static ExtendedAdDto fromExtendedAdDto(ExtendedAd extendedAd){
        if(extendedAd == null){
            throw new NullPointerException("Tried to map null in ExtendedAdDto");
        }

        ExtendedAdDto extendedAdDto = new ExtendedAdDto();

        extendedAdDto.setPk(extendedAd.getPk());
        extendedAdDto.setAuthorFirstName(extendedAd.getAuthorFirstName());
        extendedAdDto.setAuthorLastName(extendedAd.getAuthorLastName());
        extendedAdDto.setDescription(extendedAd.getDescription());
        extendedAdDto.setEmail(extendedAd.getEmail());
        extendedAdDto.setImage(extendedAd.getImage());
        extendedAdDto.setPhone(extendedAd.getPhone());
        extendedAdDto.setPrice(extendedAd.getPrice());
        extendedAdDto.setTitle(extendedAd.getTitle());

        return extendedAdDto;
    }
}
