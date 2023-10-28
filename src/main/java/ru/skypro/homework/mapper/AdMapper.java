package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", expression = "java(getImageUrl(adEntity.getImage()))")
    public abstract Ad adEntityToAd(AdEntity adEntity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(getImageUrl(adEntity.getImage()))")
    public abstract ExtendedAd adEntityToExtendedAd(AdEntity adEntity);

    public abstract void createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd,
                                                    @MappingTarget AdEntity adEntity);

    public Ads listOfAdEntitiesToAds(List<AdEntity> list) {
        Ads allAds = new Ads();
        allAds.setCount(list.size());
        allAds.setResults(list.stream().map(this::adEntityToAd).collect(Collectors.toList()));
        return allAds;
    }

    protected String getImageUrl(Image image) {
        if (image == null) {
            return null;
        }
        return "/images/" + image.getId();
    }
}