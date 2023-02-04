package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.entity.Ads;

@Mapper
public interface AdsMapper {
    AdsDto adsToAdsDto (Ads Ads);
    Ads AdsDtoToAds(AdsDto dto);
}
