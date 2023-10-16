package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

/**
 * Класс конвертирует модель AdEntity в Ad Dto и обратно.
 */
@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(source = "userEntity.id", target = "author")
    @Mapping(source = "imageEntity.filePath", target = "image")
    Ad adEntityToAd(AdEntity adEntity);

    @Mapping(source = "userEntity.id", target = "author")
    @Mapping(source = "imageEntity.filePath", target = "image")
    List<Ad> adEntityListToAdList(List<AdEntity> adEntityList);

    @Mapping(source = "userEntity.firstName", target = "authorFirstName")
    @Mapping(source = "userEntity.lastName", target = "authorLastName")
    @Mapping(source = "userEntity.username", target = "email")
    @Mapping(source = "userEntity.phone", target = "phone")
    @Mapping(source = "imageEntity.filePath", target = "image")
    ExtendedAd adEntityToExtendedAd(AdEntity adEntity);

    AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);


}
