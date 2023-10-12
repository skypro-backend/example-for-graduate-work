package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    Ads adEntitytoAds(AdEntity adEntity);
    AdEntity adsToAdEntity(Ads ads);
}
