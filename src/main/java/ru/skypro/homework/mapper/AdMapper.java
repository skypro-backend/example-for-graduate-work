package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;

import org.springframework.stereotype.Component;

@Component
public class AdMapper {
    public AdDto entityToAdDto(Ad entity) {
        return new AdDto(entity.getAuthor().getId(), entity.getImagePath(),
                entity.getPk(), entity.getPrice(), entity.getTitle());
    }

//    public Ad adEntityToAd(Ad adEntity);
//
//    public abstract AdDto AdToAdDto(Ad Ad);
//
//    public abstract Ad adToAdEntity(Ad ad);

    public Ad entityToAdsDto(Ad entity){
        return null;
    }

    public ExtendedAdDto entityToExtendedAdsDto(Ad entity) {
        return new ExtendedAdDto(entity.getPk(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(),
                entity.getDescription(), entity.getAuthor().getUsername(), entity.getImagePath(),
                entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle());
    }

    public Ad createOrUpdateAdToEntity(CreateOrUpdateAdDto ads, User author) {
        return new Ad(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }

}