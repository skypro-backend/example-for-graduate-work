package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Image;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AdMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", expression = "java(getImageUrl(adEntity.getImage()))")
    public abstract Ad adEntityToAd(AdEntity adEntity);

    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "image", ignore = true)
    public abstract AdEntity adToAdEntity(Ad ad);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", expression = "java(getImageUrl(adEntity.getImage()))")
    public abstract ExtendedAd adEntityToExtendedAd(AdEntity adEntity);

    public abstract CreateOrUpdateAd adEntityToCreateOrUpdateAd(AdEntity adEntity);

    public abstract AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);

    protected String getImageUrl(Image image) {
        return "/images/" + image.getId();
    }
}