package ru.skypro.homework.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;

@Mapper
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDto toAdsDto(Ads ads);
    CreateAdsDto toCreateAdsDto(Ads ads);
    FullAdsDto toDullAdsDto(Ads ads);
    Ads toAds(AdsDto adsDto);
    Ads fromCreateAdsToAds(CreateAdsDto createAdsDto);
    Ads fromFullAdsToAds(FullAdsDto fullAdsDto);

}
