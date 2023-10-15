package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    Ad adEntityToAd(AdEntity adEntity); // TODO: 14.10.2023 не выгружает поля из UserEntity

    List<Ad> adEntityListToAdList(List<AdEntity> adEntityList); // TODO: 14.10.2023 не выгружает поля из UserEntity

    ExtendedAd adEntityToExtendedAd(AdEntity adEntity); // TODO: 14.10.2023 не выгружает поля из UserEntity

    AdEntity createOrUpdateAdToAdEntity(CreateOrUpdateAd createOrUpdateAd);


}
