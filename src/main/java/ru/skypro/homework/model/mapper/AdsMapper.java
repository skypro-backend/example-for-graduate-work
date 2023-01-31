package ru.skypro.homework.model.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.entity.Ads;

@Mapper
public interface AdsMapper {
    AdsDto toAdsDTO (Ads Ads);
    Ads toAds(AdsDto dto);
}
