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
    Ad adEntityToAd(AdEntity adEntity); // TODO: 14.10.2023 не передает image

    @Mapping(source = "userEntity.id", target = "author")
    List<Ad> adEntityListToAdList(List<AdEntity> adEntityList); // TODO: 14.10.2023 не передает image

    @Mapping(source = "userEntity.firstName", target = "authorFirstName")
    @Mapping(source = "userEntity.lastName", target = "authorLastName")
    @Mapping(source = "userEntity.username", target = "email")
    @Mapping(source = "userEntity.phone", target = "phone")
    ExtendedAd adEntityToExtendedAd(AdEntity adEntity); // TODO: 14.10.2023 не передает image

    AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);


}
