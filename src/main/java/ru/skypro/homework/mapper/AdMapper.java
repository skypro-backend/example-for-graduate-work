package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;

import org.springframework.stereotype.Component;

@Component
public abstract class AdMapper {
    public AdDto entityToAdsDto(AdDto entity) {
        return new AdDto(entity.getAuthor().getId(), entity.getImagePath(),
                entity.getId(), entity.getPrice(), entity.getTitle());
    }

    public abstract Ad adEntityToAd(Ad adEntity);

    public abstract AdDto AdToAdDto(Ad Ad);

    public abstract Ad adToAdEntity(Ad ad);

    public abstract Ad entityToAdsDto(Ad entity);

    public ExtendedAdDto entityToExtendedAdsDto(Ad entity) {
        return new ExtendedAdDto(entity.getId(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(), entity.getDescription(),
                entity.getAuthor().getEmail(), entity.getImagePath(),
                entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle());
    }

    public Ad createOrUpdateAdToEntity(CreateOrUpdateAdDto ads, User author) {
        return new Ad(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }
}