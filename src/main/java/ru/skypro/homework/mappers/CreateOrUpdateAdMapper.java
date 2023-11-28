package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.models.CreateOrUpdateAd;

public class CreateOrUpdateAdMapper {

    public static CreateOrUpdateAd toCreateOrUpdateAd(CreateOrUpdateAdDto createOrUpdateAdDto){
        if(createOrUpdateAdDto == null){
            throw new NullPointerException("Tried to map null in CreateOrUpdateAd");
        }

        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd();

        createOrUpdateAd.setTitle(createOrUpdateAdDto.getTitle());
        createOrUpdateAd.setPrice(createOrUpdateAdDto.getPrice());
        createOrUpdateAd.setDescription(createOrUpdateAdDto.getDescription());

        return createOrUpdateAd;

    }

    public static CreateOrUpdateAdDto fromCreateOrUpdateAd(CreateOrUpdateAd createOrUpdateAd){
        if(createOrUpdateAd == null){
            throw new NullPointerException("Tried to map null in CreateOrUpdateAdDto");
        }

        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto();

        createOrUpdateAdDto.setTitle(createOrUpdateAd.getTitle());
        createOrUpdateAdDto.setPrice(createOrUpdateAd.getPrice());
        createOrUpdateAdDto.setDescription(createOrUpdateAd.getDescription());

        return createOrUpdateAdDto;
    }
}
