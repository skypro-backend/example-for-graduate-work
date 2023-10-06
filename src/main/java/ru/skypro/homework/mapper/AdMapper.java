package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", ignore = true) //todo: add mapping from Image to image url
    Ad adEntityToAd(AdEntity adEntity);

    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "image", ignore = true) //todo: add mapping from Image to image url
    AdEntity adToAdEntity(Ad ad);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", ignore = true) //todo: add mapping from Image to image url
    ExtendedAd adEntityToExtendedAd(AdEntity adEntity);

    CreateOrUpdateAd adEntityToCreateOrUpdateAd(AdEntity adEntity);

    AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);

}